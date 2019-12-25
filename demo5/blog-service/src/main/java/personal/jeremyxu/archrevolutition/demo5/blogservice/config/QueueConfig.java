package personal.jeremyxu.archrevolutition.demo5.blogservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    public static final String BLOG_EVNETS_QUEUE_NAME = "blogEvents";

    @Bean
    public Queue blogEventsQueue() {
        return new Queue(BLOG_EVNETS_QUEUE_NAME);
    }

}