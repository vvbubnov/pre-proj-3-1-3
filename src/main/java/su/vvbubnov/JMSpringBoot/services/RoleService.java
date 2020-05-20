package su.vvbubnov.JMSpringBoot.services;


import su.vvbubnov.JMSpringBoot.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    void addRole(Role role);

    void editRole(Role role);

    void deleteRole(Role role);

    Role getRoleByName(String name);

    Role getRoleById(Long id);

    List<Role> getAllRoles();

    Set<Role> getRolesByNames(List<String> roleNames);

    Set<Role> getRolesByIds(List<Long> roleIds);
}
