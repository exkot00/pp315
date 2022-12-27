package ru.kata.spring.boot_security.demo.controller;

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

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("userList", userServiceImpl.index());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImpl.show(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(User user) {
    return "new";
}

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userServiceImpl.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id) {
        model.addAttribute("user", userServiceImpl.show(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update (@ModelAttribute("user") User user) {
        userServiceImpl.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userServiceImpl.delete(id);
        return "redirect:/users";
    }
}
