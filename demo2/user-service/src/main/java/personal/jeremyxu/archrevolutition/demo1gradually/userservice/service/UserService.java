package personal.jeremyxu.archrevolutition.demo1gradually.userservice.service;


import personal.jeremyxu.archrevolutition.demo1gradually.userservice.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void saveUser(User user);

    User findUserById(Integer userId);

    void deleteById(Integer userId);
}
