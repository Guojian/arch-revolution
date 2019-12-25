package personal.jeremyxu.archrevolutition.demo5.aggregationservice.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import personal.jeremyxu.archrevolutition.demo5.aggregationservice.dto.Blog;
import personal.jeremyxu.archrevolutition.demo5.aggregationservice.dto.BlogDTO;
import personal.jeremyxu.archrevolutition.demo5.aggregationservice.dto.UserDTO;
import personal.jeremyxu.archrevolutition.demo5.aggregationservice.service.AggregationService;
import personal.jeremyxu.archrevolutition.demo5.aggregationservice.service.BlogProxyService;
import personal.jeremyxu.archrevolutition.demo5.aggregationservice.service.UserFeignService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static personal.jeremyxu.archrevolutition.demo5.aggregationservice.config.QueueConfig.BLOG_EVNETS_QUEUE_NAME;
import static personal.jeremyxu.archrevolutition.demo5.aggregationservice.config.QueueConfig.USER_EVNETS_QUEUE_NAME;

@Service
// 监听USER_EVNETS_QUEUE_NAME、BLOG_EVNETS_QUEUE_NAME这两个队列里的消息
@RabbitListener(queues = {USER_EVNETS_QUEUE_NAME, BLOG_EVNETS_QUEUE_NAME})
public class AggregationServiceImpl implements AggregationService {
    @Autowired
    UserFeignService userFeignService;

    @Autowired
    BlogProxyService blogProxyService;

    @Autowired
    RedisTemplate<String, BlogDTO> redisTemplate;

    private static final String CACHE_KEY_PREFIX="blogDTO_";

    @Override
    public void deleteUser(Integer userId) {
        // 先删除该用户的博文
        blogProxyService.deleteBlogsByUserId(userId);
        // 再删除该用户
        userFeignService.deleteUser(userId);
    }

    @Override
    public List<BlogDTO> getBlogs() {
        // 首先从缓存里取数据，缓存为空才查询数据库，查询完数据库后，回填缓存
        Set<String> keys = redisTemplate.keys(CACHE_KEY_PREFIX + "*");
        if (keys.size() == 0) {
            refreshBlogDTOsCache();
            keys = redisTemplate.keys(CACHE_KEY_PREFIX + "*");
        }
        return redisTemplate.opsForValue().multiGet(keys);
    }

    private void refreshBlogDTOsCache() {
        // 先取到博文列表
        List<Blog> blogs = blogProxyService.getBlogs();
        // 再补充其它关联字段
        Map<String, BlogDTO> map = new HashMap<String, BlogDTO>();
        for (Blog b : blogs) {
            BlogDTO blog = convertToBlogDTO(b);
            map.put(CACHE_KEY_PREFIX + blog.getId(), blog);
        }
        redisTemplate.opsForValue().multiSet(map);
    }

    @Override
    public BlogDTO getBlog(Integer blogId) {
        // 首先从缓存里取数据，缓存为空才查询数据库，查询完数据库后，回填缓存
        BlogDTO blog = redisTemplate.opsForValue().get(CACHE_KEY_PREFIX + blogId);
        if (blog == null) {
            Blog b = blogProxyService.getBlog(blogId);
            blog = convertToBlogDTO(b);
            redisTemplate.opsForValue().set(CACHE_KEY_PREFIX + blogId, blog);
        }
        return blog;
    }

    private BlogDTO convertToBlogDTO(Blog b) {
        BlogDTO blog = new BlogDTO();
        BeanUtils.copyProperties(b, blog);
        UserDTO user = userFeignService.getUser(blog.getUserId());
        blog.setNickname(user.getNickname());
        blog.setFirstName(user.getFirstName());
        blog.setLastName(user.getLastName());
        return blog;
    }

    @RabbitHandler
    public void process(String payload) {
        // 从消息队列接收数据变更事件，收到后重新加载数据并更新缓存
        refreshBlogDTOsCache();
    }
}
