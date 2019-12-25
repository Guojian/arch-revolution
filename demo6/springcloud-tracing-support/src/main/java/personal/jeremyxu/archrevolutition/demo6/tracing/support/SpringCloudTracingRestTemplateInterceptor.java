package personal.jeremyxu.archrevolutition.demo6.tracing.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpringCloudTracingRestTemplateInterceptor implements ClientHttpRequestInterceptor {

  @Autowired
  private RestTemplate restTemplate;

  @PostConstruct
  public void attachInterceptor() {
    List<ClientHttpRequestInterceptor> interceptors
        = restTemplate.getInterceptors();
    boolean hasSpringCloudTracingRestTemplateInterceptor = false;
    if (CollectionUtils.isEmpty(interceptors)) {
      interceptors = new ArrayList<>();
    } else {
      for(ClientHttpRequestInterceptor interceptor : interceptors){
        if(interceptor instanceof SpringCloudTracingRestTemplateInterceptor) {
          hasSpringCloudTracingRestTemplateInterceptor = true;
        }
      }
    }
    if (!hasSpringCloudTracingRestTemplateInterceptor) {
      interceptors.add(this);
    }
    restTemplate.setInterceptors(interceptors);
  }

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    HttpHeaders headers = request.getHeaders();
    Map<String, String> tracingCtxInfo = SpringCloudTracingStorage.getTracingCtxInfo();
    if(tracingCtxInfo != null && tracingCtxInfo.size() > 0){
      for(Map.Entry<String, String> entry : tracingCtxInfo.entrySet()){
        headers.add(entry.getKey(), entry.getValue());
      }
    }
    return execution.execute(request, body);
  }
}
