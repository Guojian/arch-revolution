package personal.jeremyxu.archrevolutition.demo5.blogservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import personal.jeremyxu.archrevolutition.demo5.blogservice.model.Blog;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Blog> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Blog> template = new RedisTemplate<String, Blog>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

}
