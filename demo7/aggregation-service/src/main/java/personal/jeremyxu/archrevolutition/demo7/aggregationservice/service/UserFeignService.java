package personal.jeremyxu.archrevolutition.demo7.aggregationservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.jeremyxu.archrevolutition.demo7.aggregationservice.dto.UserDTO;

// 以`服务名称+端口`方式调用
//@FeignClient(value = "user-service")
@FeignClient(value = "user-service", url = "http://user-service:8081")
@RequestMapping(value = "/api/v1")
public interface UserFeignService {
    @DeleteMapping("/users/{id}")
    Integer deleteUser(@PathVariable("id") Integer userId);

    @GetMapping("/users/{id}")
    UserDTO getUser(@PathVariable("id") Integer userId);
}
