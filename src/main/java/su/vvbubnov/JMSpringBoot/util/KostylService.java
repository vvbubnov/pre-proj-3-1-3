package su.vvbubnov.JMSpringBoot.util;

import org.springframework.security.core.context.SecurityContextHolder;
import su.vvbubnov.JMSpringBoot.models.Role;
import su.vvbubnov.JMSpringBoot.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class KostylService {

    /**
     * Костылирующий метод, возвращающий id ролей добавляемого/изменяемого
     * пользователя. Возник из-за продолжительной борьбы с особенностями
     * работы checkbox-ов. Возможно есть варианты избавиться от него...
     * */
    public static List<Long> getRoleIds(User user) {
        return user.getRoles()
                .stream()
                .map(Role::getId)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает залогиненного пользователя
     * */
    public static User getPrincipal() {
        return  (User) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();
    }
}
