package recipes.controllers;

import org.springframework.web.multipart.MultipartFile;
import recipes.module.Recipe;
import recipes.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

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
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        model.addAttribute("images", recipe.getImages());
        return "recipe-info";
    }

    @PostMapping("/recipe/create")
    public String createRecipe(@RequestParam("file_1") MultipartFile file_1, @RequestParam("file_2") MultipartFile file_2,
                               @RequestParam("file_3") MultipartFile file_3, @RequestParam("file_4") MultipartFile file_4,
                               @RequestParam("file_5") MultipartFile file_5, Recipe recipe) throws IOException {
        recipeService.saveRecipe(recipe, file_1, file_2, file_3, file_4, file_5);
        return "redirect:/";
    }

    @PostMapping("/recipe/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return "redirect:/";
    }

}
