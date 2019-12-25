package personal.jeremyxu.archrevolutition.demo2.demo2main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import personal.jeremyxu.archrevolutition.demo2.demo2main.service.BlogService;
import personal.jeremyxu.archrevolutition.demo2.demo2main.service.UserService;
import personal.jeremyxu.archrevolutition.demo2.demo2main.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
//    @Autowired
//    UserRepository userRepository;

    @Autowired
    BlogService blogService;

    @Autowired
    RestTemplate restTemplate;

    @Value("${user-service.url.prefix}")
    String userServiceUrlPrefix;

    @Override
    public List<User> findAll() {
//        return userRepository.findAll();
        String url = userServiceUrlPrefix + "/users";
        return restTemplate.getForObject(url, List.class);
    }

    @Override
    public void saveUser(User user) {
//        userRepository.saveAndFlush(user);
        String url = userServiceUrlPrefix + "/users";
        restTemplate.postForEntity(url, user, User.class);
    }

    @Override
    public User findUserById(Integer userId) {
//        return userRepository.findById(userId).get();
        String url = userServiceUrlPrefix + "/users/" + userId;
        return restTemplate.getForObject(url, User.class);
    }

    @Override
    public void deleteById(Integer userId) {
        blogService.deleteByUserId(userId);

//        userRepository.deleteById(userId);
//        userRepository.flush();
        String url = userServiceUrlPrefix + "/users/" + userId;
        restTemplate.delete(url);

    }
}
