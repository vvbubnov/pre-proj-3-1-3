package su.vvbubnov.JMSpringBoot.services;

import su.vvbubnov.JMSpringBoot.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void deleteUser(Long id);

    User getUserById(Long id);

    void addUser(User user, List<Long> roleIds);

    void editUser(User user, List<Long> roleIds);
}
