package personal.jeremyxu.archrevolutition.demo7.frontendservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class FrontendController {

// 禁用DiscoveryClient
//    @Autowired
//    DiscoveryClient discoveryClient;

    @Value("${app.apiGatewayUrl}")
    String apiGatewayURL;

    @RequestMapping(value = "/api/injectClientConfig", method = RequestMethod.GET, produces = "text/javascript; charset=utf-8")
    public @ResponseBody String injectClientInfo(HttpServletRequest request, HttpSession session){
//        List<ServiceInstance> instances = discoveryClient.getInstances("apigateway-service");
//        if (instances.size() > 0){
//            ServiceInstance instance = instances.get(0);
//            String apiGatewayURL = String.format("http://%s:%d", instance.getHost(), instance.getPort());
//            return String.format("window.API_GATEWAY_URL='%s';", apiGatewayURL);
//        }
//        return "if(window.console){window.console.log('not discovery api gateway address, use default.');}";

        // 直接输出API Gateway的访问地址
        return String.format("window.API_GATEWAY_URL='%s';", apiGatewayURL);
    }
}
