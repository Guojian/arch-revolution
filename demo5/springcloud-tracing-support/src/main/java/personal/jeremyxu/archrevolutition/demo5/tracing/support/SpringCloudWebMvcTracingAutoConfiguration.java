package personal.jeremyxu.archrevolutition.demo5.tracing.support;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnClass(WebMvcConfigurer.class)
public class SpringCloudWebMvcTracingAutoConfiguration implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 增加WebMvc拦截器，在收到请求时，将Spring Cloud Tracing相关的请求头保存在线程上下文中
    registry.addInterceptor(new SpringCloudTracingWebWvcInterceptor()).addPathPatterns("/**");
  }
}
