package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> index() {  //getAll
        return  userRepository.findAll();
    }

    @Override
    public User show(int id) { //select1
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(User user) {
         userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}

