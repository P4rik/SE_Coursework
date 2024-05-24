package com.example.buysell.controllers;

import com.example.buysell.module.Recipe;
import com.example.buysell.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final RecipeService recipeService;
    @GetMapping("/")
    public String recipes(Model model) {
        model.addAttribute("recipes", recipeService.listRecipes());
        return "recipes";
    }
    @GetMapping("/recipe/{id}")
    public String RecipeInfo(@PathVariable Long id, Model model) {
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
