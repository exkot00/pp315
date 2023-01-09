package ru.kata.spring.boot_security.demo.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class RestAdminController {
    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    public RestAdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl, RoleRepository roleRepository, UserRepository userRepository) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping() //гл страница админа с юзерами ++
    public ResponseEntity<List<User>> showUsers() {
        return ResponseEntity.ok(userServiceImpl.index());
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser (@PathVariable int id) {
        return ResponseEntity.ok(userServiceImpl.show(id));
    }
    @GetMapping("/userAuth") //re
    public ResponseEntity<User> showAuthUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new ResponseEntity<> (user, HttpStatus.OK);
    }

    @PostMapping("/newAddUser")
    public ResponseEntity<User> saveNewUser(@RequestBody User user) {
        return ResponseEntity.ok(userServiceImpl.save(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userServiceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/users/{id}")
    public ResponseEntity<HttpStatus> userSaveEdit(@RequestBody User user, @PathVariable("id") int id) {
        userServiceImpl.update(user);
        return new ResponseEntity<> (HttpStatus.OK);
    }

}
