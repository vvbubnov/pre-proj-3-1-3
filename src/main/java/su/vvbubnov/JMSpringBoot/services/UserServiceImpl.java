package su.vvbubnov.JMSpringBoot.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import su.vvbubnov.JMSpringBoot.models.User;
import su.vvbubnov.JMSpringBoot.repositories.UserRepo;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleService roleServiceImpl;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepo userRepo,
            RoleService roleServiceImpl,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepo = userRepo;
        this.roleServiceImpl = roleServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void addUser(User user, List<Long> roleIds) {
        user.setRoles(roleServiceImpl.getRolesByIds(roleIds));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    public void editUser(User user, List<Long> roleIds) {
        user.setRoles(roleServiceImpl.getRolesByIds(roleIds));
        if (user.getPassword().isEmpty() || user.getPassword() == null) {
            user.setPassword(getUserById(user.getId()).getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

}
