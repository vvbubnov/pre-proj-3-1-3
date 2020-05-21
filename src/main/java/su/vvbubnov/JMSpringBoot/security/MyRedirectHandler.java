package su.vvbubnov.JMSpringBoot.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class MyRedirectHandler {
    public static String getTargetUrl(Authentication authentication) {
        /*
        * todo возможно можно попробовать по другому:
        *  1. Если у authority можно было бы достать "id" (пока мне не удалось)
        *  2. приоретет доступа можно было бы организовать через неё
        *   админы-юзеры-...лохи...
        *  3. через stream находим минимальную id
        *  4. через switch-case редиректим
        *   "1" /admin  "2" /user ...
        * */

        List<String> authorities = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (authorities.contains("ROLE_ADMIN")) {
            return "/admin";
        } else if (authorities.contains("ROLE_USER")) {
            return "/user";
        } else if (authorities.contains("ROLE_SCHOOLBOY")) {
            return "/user";
        }

        System.out.println("\"++++++++++++++++++++++      NO ROLE FOR REDIRECT!!\"");

        return null;
    }
}
