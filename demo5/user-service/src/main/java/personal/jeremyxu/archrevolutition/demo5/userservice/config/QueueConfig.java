package personal.jeremyxu.archrevolutition.demo5.userservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    public static final String USER_EVNETS_QUEUE_NAME = "userEvents";

    @Bean
    public Queue userEventsQueue() {
        return new Queue(USER_EVNETS_QUEUE_NAME);
    }

}