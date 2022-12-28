package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userServiceImpl;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    public AdminController(UserServiceImpl userServiceImpl, RoleRepository roleRepository, UserRepository userRepository) {
        this.userServiceImpl = userServiceImpl;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    //    @GetMapping("/user/{id}/edit")
//    public String edit(Model model,@PathVariable("id") int id) {
//        model.addAttribute("user", userServiceImpl.show(id));
//        return "edit";
//    }


    @GetMapping() //гл страница админа с юзерами
    public String index(Model model) {
        model.addAttribute("userList", userServiceImpl.index());
        return "index";
    }
    @GetMapping("/user/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImpl.show(id));
        return "show";
    }


    @PatchMapping("/user/{id}")
    public String update (@ModelAttribute("user") User user) {
        userServiceImpl.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("user/{id}")
    public String delete(@PathVariable("id") int id) {
        userServiceImpl.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/user/new")
    public String newUser(User user, Model model) {

        model.addAttribute("user", new User());
        List<Role> listRoles = roleRepository.findAll();
        model.addAttribute("listRoles", listRoles);
    return "new";
    }

    @PostMapping("/user/new")
    public String create(@ModelAttribute("user") User user, Model model) {

        List<Role> listRoles = roleRepository.findAll();
        model.addAttribute("listRoles", listRoles);
        userServiceImpl.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/user/{id}/edit")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).get();
        List<Role> listRoles = roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "edit";
    }




}
