package recipes.services;

import org.springframework.web.multipart.MultipartFile;
import recipes.module.Image;
import recipes.module.Recipe;
import recipes.module.User;
import recipes.repositories.RecipeRepositorie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import recipes.repositories.UserRepository;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepositorie recipeRepository;
    private final UserRepository userRepository;

    public List<Recipe> listRecipes(String title, String catalogy) {
        if (title != null && !title.isEmpty()) {
            return recipeRepository.findByTitle(title);
        } else if (catalogy != null && !catalogy.isEmpty()) {
            return recipeRepository.findByCatalogy(catalogy);
        }
        return recipeRepository.findAll();
    }


    public void saveRecipe(Principal principal, Recipe recipe, MultipartFile file_1, MultipartFile file_2, MultipartFile file_3, MultipartFile file_4, MultipartFile file_5) throws IOException {
        recipe.setUser(getUserByPrincipal(principal));
        Image image_1;
        Image image_2;
        Image image_3;
        Image image_4;
        Image image_5;

        if (file_1.getSize() != 0) {
            image_1 = toImageEntity(file_1);
            image_1.setPreviewImage(true);
            recipe.addImageToRecipe(image_1);
        }
        if (file_2.getSize() != 0) {
            image_2 = toImageEntity(file_2);
            recipe.addImageToRecipe(image_2);
        }
        if (file_3.getSize() != 0) {
            image_3 = toImageEntity(file_3);
            recipe.addImageToRecipe(image_3);
        }
        if (file_4.getSize() != 0) {
            image_4 = toImageEntity(file_4);
            recipe.addImageToRecipe(image_4);
        }
        if (file_5.getSize() != 0) {
            image_5 = toImageEntity(file_5);
            recipe.addImageToRecipe(image_5);
        }
        log.info("Saving new Recipe. Title: {}; Author email: {}", recipe.getTitle(), recipe.getUser().getEmail());
        Recipe recipeFromDb = recipeRepository.save(recipe);
        recipeFromDb.setPreviewImageId((recipeFromDb.getImages().get(0).getId()));
        recipeRepository.save(recipe);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return  new User();
        return userRepository.findByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }
}
