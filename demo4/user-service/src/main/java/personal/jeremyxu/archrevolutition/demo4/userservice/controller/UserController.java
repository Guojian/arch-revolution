package personal.jeremyxu.archrevolutition.demo4.userservice.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.jeremyxu.archrevolutition.demo4.userservice.model.User;
import personal.jeremyxu.archrevolutition.demo4.userservice.service.UserService;

import java.util.List;

/**
 * Created by jeremyxu on 2019/3/26.
 */
@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        // 查询user表中所有记录
        return userService.findAll();
    }

    // post请求，处理添加用户请求
    @PostMapping(value = "/users")
    public ResponseEntity addUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    // 查看用户详情
    // @PathVariable可以收集url中的变量，需匹配的变量用{}括起来
    // 例如：访问 localhost:8080/admin/users/show/1 ，将匹配 id = 1
    @GetMapping("/users/{id}")
    public ResponseEntity getUser(@PathVariable("id") Integer userId) {
        // 找到userId所表示的用户
        User user = userService.findUserById(userId);
        if (user == null) {
            return new ResponseEntity("No User found for ID " + userId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    // 更新用户信息 操作
    @PutMapping("/users/{id}")
    public ResponseEntity updateCustomer(@PathVariable("id") Integer userId, @RequestBody User user) {
        // 更新用户信息
        User oldUser = userService.findUserById(userId);

        if (null == oldUser) {
            return new ResponseEntity("No User found for ID " + userId, HttpStatus.NOT_FOUND);
        }

        BeanUtils.copyProperties(user, oldUser);
        userService.saveUser(oldUser);

        return new ResponseEntity(oldUser, HttpStatus.OK);
    }


    // 删除用户
    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Integer userId) {
        // 删除id为userId的用户
        userService.deleteById(userId);

        return new ResponseEntity(userId, HttpStatus.OK);
    }
}
