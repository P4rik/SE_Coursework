package recipes.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import recipes.module.User;
import recipes.services.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.isValidPassword(user.getPassword())) {
            model.addAttribute("errorMessageReg", "Пароль має містити від 8 до 20 символів, включати як мінімум одну велику літеру та не містити пробілів!");
            return "registration"; // Return to registration page with error message
        }
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessageReg", "Користувач з таким email: " + user.getEmail() + " вже існує!");
            return "registration"; // Return to registration page with error message
        }
        return "redirect:/login"; // Redirect to login page after successful registration
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("recipes", user.getRecipes());
        return "user-info";
    }

    @GetMapping("/myprofile")
    public String profile(Principal principal,
                          Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "myprofile";
    }
}