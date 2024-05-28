package com.example.buysell.repositories;

import com.example.buysell.module.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepositorie extends JpaRepository<Recipe, Long> {
    List<Recipe> findByTitle(String title);
}
