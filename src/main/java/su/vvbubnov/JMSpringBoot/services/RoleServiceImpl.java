package su.vvbubnov.JMSpringBoot.services;

import org.springframework.stereotype.Service;
import su.vvbubnov.JMSpringBoot.models.Role;
import su.vvbubnov.JMSpringBoot.repositories.RoleRepo;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void addRole(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void editRole(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepo.delete(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepo.findByRoleName(name);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepo.findById(id).orElse(null);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Set<Role> getRolesByNames(List<String> roleNames) {
        return roleRepo.findAllByRoleNameIn(roleNames);
    }

    @Override
    public Set<Role> getRolesByIds(List<Long> roleIds) {
        return roleRepo.findAllByIdIn(roleIds);
    }

}
