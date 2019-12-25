package personal.jeremyxu.archrevolutition.demo5.tracing.support;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Map;

/**
 * 增加Feign拦截器，实现spring cloud下feign调用传递Istio Tracing的请求头
 */
public class SpringCloudTracingFeignClientRequestInterceptor implements RequestInterceptor {
    public SpringCloudTracingFeignClientRequestInterceptor() {
    }

    @Override
    public void apply(RequestTemplate input) {
        Map<String, String> tracingCtxInfo = SpringCloudTracingStorage.getTracingCtxInfo();
        if(tracingCtxInfo != null && tracingCtxInfo.size() > 0){
            for(Map.Entry<String, String> entry : tracingCtxInfo.entrySet()){
                input.header(entry.getKey(), entry.getValue());
            }
        }
    }
}