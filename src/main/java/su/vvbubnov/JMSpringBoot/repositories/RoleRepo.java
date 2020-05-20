package su.vvbubnov.JMSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.vvbubnov.JMSpringBoot.models.Role;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByRoleName(String name);

    Set<Role> findAllByRoleNameIn(List<String> names);

    Set<Role> findAllByIdIn(List<Long> roleIds);

}
