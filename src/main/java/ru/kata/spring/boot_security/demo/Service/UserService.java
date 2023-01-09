package ru.kata.spring.boot_security.demo.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> index();

    User show(int id);

    User save(User user);

    User update(User user);

    void delete(int id);
}
