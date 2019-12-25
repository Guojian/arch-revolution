package personal.jeremyxu.archrevolutition.demo7.blogservice.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import personal.jeremyxu.archrevolutition.demo7.blogservice.model.Blog;
import personal.jeremyxu.archrevolutition.demo7.blogservice.repository.BlogRepository;
import personal.jeremyxu.archrevolutition.demo7.blogservice.service.BlogService;
import personal.jeremyxu.archrevolutition.demo7.blogservice.config.QueueConfig;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    RedisTemplate<String, Blog> redisTemplate;

    @Autowired
    protected StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    private static final String CACHE_KEY_PREFIX="blog_";
    private static final String CACHE_KEY_INDEX_USER_ID_PREFIX="blogIndexByUserId_";

    @Override
    public List<Blog> findAll() {
        // 首先从缓存里取数据，缓存为空才查询数据库，查询完数据库后，回填缓存
        Set<String> keys = redisTemplate.keys(CACHE_KEY_PREFIX + "*");
        if (keys.size() == 0) {
            List<Blog> blogs = blogRepository.findAll();

            Map<String, Blog> map = new HashMap<>();
            Map<String, String> indexByUserIdMap = new HashMap<>();
            for(Blog blog : blogs){
                map.put(CACHE_KEY_PREFIX + blog.getId(), blog);
                indexByUserIdMap.put(CACHE_KEY_INDEX_USER_ID_PREFIX + blog.getId() + "_" + blog.getUserId(), String.valueOf(blog.getId()));
            }
            redisTemplate.opsForValue().multiSet(map);
            stringRedisTemplate.opsForValue().multiSet(indexByUserIdMap);

            keys = redisTemplate.keys(CACHE_KEY_PREFIX + "*");
        }
        return redisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public void save(Blog blog) {
        // 保存时更新缓存
        blogRepository.saveAndFlush(blog);

        redisTemplate.opsForValue().set(CACHE_KEY_PREFIX + blog.getId(), blog);
        stringRedisTemplate.delete(stringRedisTemplate.keys(CACHE_KEY_INDEX_USER_ID_PREFIX + blog.getId() + "_*"));
        stringRedisTemplate.opsForValue().set(CACHE_KEY_INDEX_USER_ID_PREFIX + blog.getId() + "_" + blog.getUserId(), String.valueOf(blog.getId()));

        // 通过消息队列发送数据变更事件
        this.rabbitTemplate.convertAndSend(QueueConfig.BLOG_EVNETS_QUEUE_NAME, "BlogCreatedOrUpdated");
    }

    @Override
    public Blog findById(int blogId) {
        // 首先从缓存里取数据，缓存里没有相应数据才查询数据库，查询完数据库后，回填缓存
        Blog blog = redisTemplate.opsForValue().get(CACHE_KEY_PREFIX + blogId);
        if (blog == null) {
            blog = blogRepository.findById(blogId).get();
            if (blog != null) {

                redisTemplate.opsForValue().set(CACHE_KEY_PREFIX + blogId, blog);
                stringRedisTemplate.opsForValue().set(CACHE_KEY_INDEX_USER_ID_PREFIX + blog.getId() + "_" + blog.getUserId(), String.valueOf(blog.getId()));

            }
        }
        return blog;
    }

    @Override
    public void deleteById(int blogId) {
        // 删除时才删除相应的缓存
        blogRepository.deleteById(blogId);
        blogRepository.flush();

        List<String> cacheKeys = new ArrayList<>();
        cacheKeys.add(CACHE_KEY_PREFIX + blogId);
        cacheKeys.addAll(stringRedisTemplate.keys(CACHE_KEY_INDEX_USER_ID_PREFIX + blogId + "_*"));
        redisTemplate.delete(cacheKeys);

        // 通过消息队列发送数据变更事件
        this.rabbitTemplate.convertAndSend(QueueConfig.BLOG_EVNETS_QUEUE_NAME, "BlogDeleted");
    }

    @Override
    public void deleteByUserId(Integer userId) {
        // 删除时才删除相应的缓存
        blogRepository.deleteBlogByUserId(userId);
        blogRepository.flush();

        Set<String> keys = stringRedisTemplate.keys(CACHE_KEY_INDEX_USER_ID_PREFIX + "*_" + userId);
        List<String> blogIds = stringRedisTemplate.opsForValue().multiGet(keys);
        List<String> cacheKeys = new ArrayList<>();
        for(String blogId : blogIds) {
            cacheKeys.add(CACHE_KEY_PREFIX + blogId);
            cacheKeys.addAll(stringRedisTemplate.keys(CACHE_KEY_INDEX_USER_ID_PREFIX + blogId + "_*"));
        }
        redisTemplate.delete(cacheKeys);

        // 通过消息队列发送数据变更事件
        this.rabbitTemplate.convertAndSend(QueueConfig.BLOG_EVNETS_QUEUE_NAME, "MultipleBlogDeleted");
    }
}
