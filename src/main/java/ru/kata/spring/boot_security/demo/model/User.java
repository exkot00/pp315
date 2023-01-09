package ru.kata.spring.boot_security.demo.model;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "userlastname")
    private String userlastname;
    @Column(name = "age")
    private int age;
    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
               joinColumns = {@JoinColumn(name = "userId")},
               inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles = new HashSet<>();


    @Column(name = "login")
    private String login;

    @Column(name = "pass")
    private String pass;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return getPass();
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserlastname() {
        return userlastname;
    }

    public void setUserlastname(String userlastname) {
        this.userlastname = userlastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public User(int id, String username, String userlastname, int age, String email, Set<Role> roles, String login, String pass) {
        this.id = id;
        this.username = username;
        this.userlastname = userlastname;
        this.age = age;
        this.email = email;
        this.roles = roles;
        this.login = login;
        this.pass = pass;
    }
    public void addRole(Role role) {
        roles.add(role);
    }
    public String getRoleString() {
        return roles.stream().map(role -> role.getName().replace("ROLE_", "")).collect(Collectors.joining(" "));
    }

    public void setRoles(String[] roles) {
        List<Role> roleSet = new ArrayList<>();
        for (String role : roles) {
            if (role != null) {
                if (role.equals("ROLE_ADMIN")) {
                    roleSet.add(new Role(1, role));
                }
                if (role.equals("ROLE_USER")) {
                    roleSet.add(new Role(2, role));
                }
            }
        }
        this.roles = roleSet.stream().collect(Collectors.toSet());
    }
}
