package personal.jeremyxu.archrevolutition.demo2.demo2main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Demo2MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo2MainApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
