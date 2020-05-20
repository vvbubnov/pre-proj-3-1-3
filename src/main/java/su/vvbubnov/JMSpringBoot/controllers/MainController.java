package su.vvbubnov.JMSpringBoot.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import su.vvbubnov.JMSpringBoot.models.User;
import su.vvbubnov.JMSpringBoot.services.RoleService;
import su.vvbubnov.JMSpringBoot.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserService userServiceImpl;
    private final RoleService roleServiceImpl;

    public MainController(UserService userServiceImpl, RoleService roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/")
    public String printIndex(ModelMap modelMap, String statusMessage) {
        return "index";
    }

    @GetMapping("user")
    public String userPage(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        modelMap.addAttribute("loggedUser", principal);
        modelMap.addAttribute("statusMessage", "All correct");
        return "userPage";
    }

    @GetMapping("admin")
    public String adminPage(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        modelMap.addAttribute("loggedUser", principal);
        modelMap.addAttribute("users", userServiceImpl.getAllUsers());
        modelMap.addAttribute("possibleRoles", roleServiceImpl.getAllRoles());
        return "adminPage";
    }

    @GetMapping("user/erotica")
    public String showErotica() {
        return "erotica";
    }

}
