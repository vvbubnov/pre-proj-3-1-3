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

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/")
    public String printIndex() {
        return "index";
    }

    @GetMapping("user")
    public String userPage() {
        return "userPage";
    }

    @GetMapping("admin")
    public String adminPage() {
        return "adminPage";
    }

    @GetMapping("user/erotica")
    public String showErotica() {
        return "erotica";
    }

}
