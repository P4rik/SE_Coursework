package recipes.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import recipes.module.User;
import recipes.services.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private  final UserService userService;
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String createUser(User user, Model modul) {
        if (!userService.createUser(user)){
            modul.addAttribute("errorMessage","користувач з email: " + user.getEmail() + "вже існує");
            return "registration";
        }
        return "redirect:/login";
    }
    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("recipes", user.getRecipes());
        return "user-info";
    }
}
