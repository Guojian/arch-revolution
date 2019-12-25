package personal.jeremyxu.archrevolutition.demo7.aggregationservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import personal.jeremyxu.archrevolutition.demo7.aggregationservice.dto.BlogDTO;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, BlogDTO> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, BlogDTO> template = new RedisTemplate<String, BlogDTO>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }


}
