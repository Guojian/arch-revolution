package personal.jeremyxu.archrevolutition.demo2.demo2main.service;

import personal.jeremyxu.archrevolutition.demo2.demo2main.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void saveUser(User user);

    User findUserById(Integer userId);

    void deleteById(Integer userId);
}
