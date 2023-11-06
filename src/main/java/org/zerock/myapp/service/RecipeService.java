package org.zerock.myapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.myapp.domain.Recipe;
import org.zerock.myapp.domain.RecipeDTO;

import java.util.List;

public interface RecipeService {

    public abstract void recipeWrite(RecipeDTO recipeDTO, String LoggedInUserId);
    public abstract List<Recipe> getAllRecipes();
    public abstract List<Recipe> getAllRecipesOrderedByNumDesc();
    public abstract Page<Recipe> getPagedRecipes(Pageable pageable);
    public abstract Recipe getRecipeByNum(Long num);
    public abstract void updateViewCount(Long num);

} // end interface