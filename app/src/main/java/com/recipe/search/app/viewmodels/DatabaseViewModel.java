package com.recipe.search.app.viewmodels;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.recipe.search.app.models.RecipeDatabase;
import com.recipe.search.app.repositories.DatabaseRepository;

import java.util.List;


public class DatabaseViewModel extends ViewModel {

    private DatabaseRepository repos;

    public DatabaseViewModel(){
        repos = DatabaseRepository.getInstance();
    }

    public LiveData<List<RecipeDatabase>> getRecipes(){
        return repos.getRecipes();
    }

    public void getData(String title, String desc, String image) {
        repos.insertData(title, desc, image);
    }

    public void input(String title, String desc, String image, Uri pathFile){
        repos.input(title, desc, image, pathFile);
    }
}
