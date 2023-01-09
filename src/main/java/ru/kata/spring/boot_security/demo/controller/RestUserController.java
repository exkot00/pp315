package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.model.User;
@RestController
@RequestMapping("/api/")
public class RestUserController {

    @GetMapping("/userPage")
        public ResponseEntity<User> showAuthUser(Authentication authentication) {
            User user = (User) authentication.getPrincipal();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
}

