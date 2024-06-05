package recipes.repositories;

import recipes.module.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepositorie extends JpaRepository<Recipe, Long> {
    List<Recipe> findByTitle(String title);
    List<Recipe> findByCatalogy(String catalogy);

}

