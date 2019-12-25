package personal.jeremyxu.archrevolutition.demo6.tracing.support;

import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringCloudTracingAutoConfiguration {

  @Bean
  @ConditionalOnClass(RequestInterceptor.class)
  public RequestInterceptor springCloudTracingFeignClientRequestInterceptor(){
    return new SpringCloudTracingFeignClientRequestInterceptor();
  }

  @Bean
  @ConditionalOnBean(RestTemplate.class)
  public SpringCloudTracingRestTemplateInterceptor springCloudTracingRestTemplateInterceptor(){
    return new SpringCloudTracingRestTemplateInterceptor();
  }
}
