package ru.kata.spring.boot_security.demo.controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userServiceImpl;
    private final RoleRepository roleRepository;

    public AdminController(UserServiceImpl userServiceImpl,  RoleRepository roleRepository) {
        this.userServiceImpl = userServiceImpl;
        this.roleRepository = roleRepository;
    }


    @GetMapping() //гл страница админа с юзерами ++
    public String index(Model model, @CurrentSecurityContext(expression = "authentication.principal") User principal) {
        model.addAttribute("userList", userServiceImpl.index());
        model.addAttribute("user", principal);
        List<Role> listRoles = roleRepository.findAll();
        model.addAttribute("listRoles", listRoles);
         return "adminPage";
    }
    @GetMapping("/user")
    public String admusr(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("admin", user);
        return "admusrPage";
    }

}
