package personal.jeremyxu.archrevolutition.demo3.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.jeremyxu.archrevolutition.demo3.userservice.model.User;
import personal.jeremyxu.archrevolutition.demo3.userservice.repository.UserRepository;
import personal.jeremyxu.archrevolutition.demo3.userservice.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

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
        userRepository.deleteById(userId);
        userRepository.flush();
    }
}
