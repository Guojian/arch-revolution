package personal.jeremyxu.archrevolutition.demo5.userservice.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import personal.jeremyxu.archrevolutition.demo5.userservice.model.User;
import personal.jeremyxu.archrevolutition.demo5.userservice.repository.UserRepository;
import personal.jeremyxu.archrevolutition.demo5.userservice.service.UserService;
import personal.jeremyxu.archrevolutition.demo5.userservice.config.QueueConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RedisTemplate<String, User> redisTemplate;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    private static final String CACHE_KEY_PREFIX="user_";

    @Override
    public List<User> findAll() {
        // 首先从缓存里取数据，缓存为空才查询数据库，查询完数据库后，回填缓存
        Set<String> keys = redisTemplate.keys(CACHE_KEY_PREFIX + "*");
        if (keys.size() == 0) {
            List<User> users = userRepository.findAll();
            Map<String, User> map = new HashMap<>();
            for(User user : users){
              map.put(CACHE_KEY_PREFIX + user.getId(), user);
            }
            redisTemplate.opsForValue().multiSet(map);
            keys = redisTemplate.keys(CACHE_KEY_PREFIX + "*");
        }
        return redisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public void saveUser(User user) {
        // 保存时更新缓存
        userRepository.saveAndFlush(user);
        redisTemplate.opsForValue().set(CACHE_KEY_PREFIX + user.getId(), user);

        // 通过消息队列发送数据变更事件
        this.rabbitTemplate.convertAndSend(QueueConfig.USER_EVNETS_QUEUE_NAME, "UserCreatedOrUpdated");
    }

    @Override
    public User findUserById(Integer userId) {
        // 首先从缓存里取数据，缓存里没有相应数据才查询数据库，查询完数据库后，回填缓存
        User user = redisTemplate.opsForValue().get(CACHE_KEY_PREFIX + userId);
        if (user == null) {
            user = userRepository.findById(userId).get();
            if (user != null) {
                redisTemplate.opsForValue().set(CACHE_KEY_PREFIX + userId, user);
            }
        }
        return user;
    }

    @Override
    public void deleteById(Integer userId) {
        // 删除时才删除相应的缓存
        userRepository.deleteById(userId);
        userRepository.flush();
        redisTemplate.delete(CACHE_KEY_PREFIX + userId);

        // 通过消息队列发送数据变更事件
        this.rabbitTemplate.convertAndSend(QueueConfig.USER_EVNETS_QUEUE_NAME, "UserDeleted");
    }
}
