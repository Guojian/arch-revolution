package personal.jeremyxu.archrevolutition.demo7.aggregationservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    public static final String USER_EVNETS_QUEUE_NAME = "userEvents";

    public static final String BLOG_EVNETS_QUEUE_NAME = "blogEvents";

    @Bean
    public Queue blogEventsQueue() {
        return new Queue(BLOG_EVNETS_QUEUE_NAME);
    }

    @Bean
    public Queue userEventsQueue() {
        return new Queue(USER_EVNETS_QUEUE_NAME);
    }

}