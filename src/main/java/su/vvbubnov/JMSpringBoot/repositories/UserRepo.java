package su.vvbubnov.JMSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.vvbubnov.JMSpringBoot.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByLogin(String login);

}
