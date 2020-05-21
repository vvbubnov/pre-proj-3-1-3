package su.vvbubnov.JMSpringBoot.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import su.vvbubnov.JMSpringBoot.models.Role;
import su.vvbubnov.JMSpringBoot.models.User;
import su.vvbubnov.JMSpringBoot.services.RoleService;
import su.vvbubnov.JMSpringBoot.services.UserService;
import su.vvbubnov.JMSpringBoot.util.KostylService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class MainRestController {
    private final UserService userServiceImpl;
    private final RoleService roleServiceImpl;

    public MainRestController(UserService userServiceImpl, RoleService roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping("/admin/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userServiceImpl.getUserById(id));
    }

    @GetMapping("/admin/users")
    public List<User> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @GetMapping("/admin/roles")
    public List<Role> getAllRoles() {
        return roleServiceImpl.getAllRoles();
    }

    @PostMapping("/admin/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userServiceImpl.addUser(user, KostylService.getRoleIds(user));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/admin/edit/{id}")
    public ResponseEntity<User> editUser(@RequestBody User user) {
        userServiceImpl.editUser(user, KostylService.getRoleIds(user));
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getPrincipal")
    public User getPrincipal() {
        return KostylService.getPrincipal();
    }

    @GetMapping("/isAdmin")
    public boolean isAdmin() {
        return KostylService.isAdmin();
    }

}
