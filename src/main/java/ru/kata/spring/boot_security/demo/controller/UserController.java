package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/user") //профиль юзера
    public String showUserPanel(@CurrentSecurityContext(expression = "authentication.principal") User principal, Model model) {
        model.addAttribute("user", principal);
        return "user";
    }
    @GetMapping("/user/{id}") //Информация о юзере
    public String showUserInfo(@CurrentSecurityContext(expression = "authentication.principal") User principal, Model model) {
        model.addAttribute("user", principal);
        return "user-info";
    }





}
