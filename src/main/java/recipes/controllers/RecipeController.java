package recipes.controllers;

import recipes.module.Recipe;
import recipes.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    @GetMapping("/")
    public String recipes(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("recipes", recipeService.listRecipes(title));
        return "recipes";
    }
    @GetMapping("/recipe/{id}")
    public String recipeInfo(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(id));
        return "recipe-info";
    }
    @PostMapping("/recipe/create")
    public String createRecipe(Recipe recipe) {
        recipeService.saveRecipe(recipe);
        return "redirect:/";
    }

    @PostMapping("/recipe/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return "redirect:/";
    }

}
