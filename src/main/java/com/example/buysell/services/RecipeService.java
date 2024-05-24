package com.example.buysell.services;

import com.example.buysell.module.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {
    private List<Recipe> recipes = new ArrayList<>();
    private long ID = 0;
    {
        recipes.add(new Recipe(++ID,"Шарлотка","Ну тіпа да","Cунь хуй в чай","bRyder"));
    }
    public List<Recipe> listRecipes() { return recipes; }

    public void saveRecipe(Recipe recipe) {
        recipe.setId(++ID);
        recipes.add(recipe);
    }

    public void deleteRecipe(Long id) {
        recipes.removeIf(recipe -> recipe.getId().equals(id));
    }

    public Recipe getRecipeById(Long id) {
        for (Recipe recipe : recipes) {
            if (recipe.getId().equals(id)) return recipe;
        }
        return null;
    }
}
