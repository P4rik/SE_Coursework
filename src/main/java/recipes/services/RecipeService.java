package recipes.services;

import recipes.module.Recipe;
import recipes.repositories.RecipeRepositorie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepositorie recipeRepository;
    public List<Recipe> listRecipes(String title) {
        if (title != null) return recipeRepository.findByTitle(title);
        return recipeRepository.findAll();
    }

    public void saveRecipe(Recipe recipe) {
        log.info("Saving new {}", recipe);
        recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }
}
