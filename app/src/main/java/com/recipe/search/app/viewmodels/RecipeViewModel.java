package com.recipe.search.app.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.recipe.search.app.models.APIModel;
import com.recipe.search.app.repositories.RecipeRepository;

import java.util.List;


public class RecipeViewModel extends ViewModel {

    private RecipeRepository mRecipeRepository;


    public RecipeViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
    }

    public LiveData<List<APIModel>> getRecipes(){
        return mRecipeRepository.getRecipes();
    }

    public void getData(String query) { //ovde uzima iz mainactivity i salje repostuory
        mRecipeRepository.getData(query);
    }

}
