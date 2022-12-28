package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

@Controller
public class RegistrationController {
    private final UserServiceImpl userServiceImpl;
    private final RoleRepository roleRepository;
    public RegistrationController(UserServiceImpl userServiceImpl, RoleRepository roleRepository) {
        this.userServiceImpl = userServiceImpl;

        this.roleRepository = roleRepository;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        List<Role> listRoles = roleRepository.findAll();
        model.addAttribute("listRoles", listRoles);
        return "reg";
    }
    @PostMapping("/registration")
    public String create(@ModelAttribute("user") User user, Model model) {
      //userServiceImpl.save(user);
        List<Role> listRoles = roleRepository.findAll();
        model.addAttribute("listRoles", listRoles);
        userServiceImpl.save(user);
        return "redirect:/home";
    }



}
