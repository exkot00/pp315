package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl, RoleRepository roleRepository, UserRepository userRepository) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    @GetMapping() //гл страница админа с юзерами ++
    public String index(Model model, @CurrentSecurityContext(expression = "authentication.principal") User principal) {
        model.addAttribute("userList", userServiceImpl.index());
        model.addAttribute("user", principal);
        List<Role> listRoles = roleRepository.findAll();
        model.addAttribute("listRoles", listRoles);
         return "index";
    }
    @GetMapping("/user/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImpl.show(id));
        return "show";
    }


    @PatchMapping("/user/{id}")
    public String update (@ModelAttribute("user") User user, Model model,
                          @RequestParam(value = "rolesSelect") String[] role) {
        for (String s : role) {
            user.addRole(roleServiceImpl.getRoleByName(s));
        }
        userServiceImpl.update(user);

        List<Role> listRoles = roleRepository.findAll();
        model.addAttribute("listRoles", listRoles);
        return "redirect:/admin";
    }

    @GetMapping("/user/new")
    public String newUser(User user, Model model) {
        model.addAttribute("user", new User());
        List<Role> listRoles = roleRepository.findAll();
        model.addAttribute("listRoles", listRoles);
    return "redirect:/admin";
    }

    @PostMapping("/user/new")
    public String create(@ModelAttribute("user") User user, Model model,
                         @RequestParam(value = "rolesSelect") String[] role) {
        List<Role> listRoles = roleRepository.findAll();
        model.addAttribute("listRoles", listRoles);

        for (String s : role) {
            user.addRole(roleServiceImpl.getRoleByName(s));
        }

        userServiceImpl.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/user/{id}/edit")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).get();
        List<Role> listRoles = roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "redirect:/admin";
    }
    @DeleteMapping("user/{id}/delete")   //удаление юзера ++
    public String delete(@PathVariable("id") int id) {
        userServiceImpl.delete(id);
        return "redirect:/admin";
    }




}
