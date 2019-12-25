package personal.jeremyxu.archrevolutition.demo1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.jeremyxu.archrevolutition.demo1.model.User;
import personal.jeremyxu.archrevolutition.demo1.repository.UserRepository;
import personal.jeremyxu.archrevolutition.demo1.service.BlogService;
import personal.jeremyxu.archrevolutition.demo1.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogService blogService;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User findUserById(Integer userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public void deleteById(Integer userId) {
        blogService.deleteByUserId(userId);

        userRepository.deleteById(userId);
        userRepository.flush();
    }
}
