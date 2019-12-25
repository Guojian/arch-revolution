package personal.jeremyxu.archrevolutition.demo4.tracing.support;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SpringCloudTracingWebWvcInterceptor extends HandlerInterceptorAdapter {

    public SpringCloudTracingWebWvcInterceptor(){
    }

    private static final String TRACE_ID_HEADER_NAME = "uber-trace-id";
    private static final String BAGGAGE_HEADER_PREFIX = "uberctx-";
    private static final String X_B3_HEADER_PREFIX = "x-b3-";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String> tracingCtxInfo = new HashMap<>();
        Enumeration<String> headerNameEnums = request.getHeaderNames();
        while(headerNameEnums.hasMoreElements()){
            String headerName = headerNameEnums.nextElement();
            if(TRACE_ID_HEADER_NAME.equalsIgnoreCase(headerName)){
                tracingCtxInfo.put(headerName, request.getHeader(headerName));
            }
            if(headerName != null && headerName.toLowerCase().startsWith(BAGGAGE_HEADER_PREFIX)){
                tracingCtxInfo.put(headerName, request.getHeader(headerName));
            }
            if(headerName != null && headerName.toLowerCase().startsWith(X_B3_HEADER_PREFIX)){
                tracingCtxInfo.put(headerName, request.getHeader(headerName));
            }
        }
        SpringCloudTracingStorage.storeTracingCtxInfo(tracingCtxInfo);
        return super.preHandle(request, response, handler);
    }
}
