package su.vvbubnov.JMSpringBoot.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        handle(httpServletRequest, httpServletResponse, authentication);
        clearAuthenticationAttributes(httpServletRequest);
    }

    private void handle(HttpServletRequest request,
                        HttpServletResponse response,
                        Authentication authentication) throws IOException {
        String targetUrl = MyRedirectHandler.getTargetUrl(authentication);

        if (response.isCommitted()) {
            System.out.println("Response has already been committed. Unable to redirect to" + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

//    private String getTargetUrl(Authentication authentication) {
//        Map<String, String> urlByRole = new HashMap<>();
//        urlByRole.put("ROLE_ADMIN", "/admin");
//        urlByRole.put("ROLE_USER", "/user");
//
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        for (GrantedAuthority authority : authorities) {
//            String authorityName = authority.getAuthority();
//            if (urlByRole.containsKey(authorityName)) {
//                return urlByRole.get(authorityName);
//            }
//        }
//
//        System.out.println("++++++++++++++++++++++      NO ROLE FOR REDIRECT!!");
//        return null;
//    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}