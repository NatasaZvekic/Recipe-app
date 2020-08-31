package com.recipe.search.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.recipe.search.app.R;
import com.recipe.search.app.adapters.OnItemClickListener;
import com.recipe.search.app.adapters.RecipesAdapter;
import com.recipe.search.app.models.APIModel;
import com.recipe.search.app.settings.AppPreferenceManager;
import com.recipe.search.app.viewmodels.RecipeViewModel;

import java.util.List;


public class FindRecipes extends AppCompatActivity {

    private RecipeViewModel mRecipeViewModel;
    private RecyclerView mRecyclerView;
    private RecipesAdapter mRecipesAdapter;
    private AppPreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferenceManager = new AppPreferenceManager(this);
        if (preferenceManager.getDarkModeState()){
            setTheme(R.style.AppThemeDark);
        }else{
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_find_recipes_);

        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecipesAdapter = new RecipesAdapter();
        mRecyclerView.setAdapter(mRecipesAdapter);
        mRecipesAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClickListener(APIModel recipe) {
                Intent intent = new Intent(FindRecipes.this, RecipeDetails.class);
                intent.putExtra("recipe", recipe);
                startActivity(intent);
            }
        });
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        subscribeObservers();
    }

    private void subscribeObservers() {
        mRecipeViewModel.getRecipes().observe(this,  new Observer<List<APIModel>>() {
            @Override
            public void onChanged(@Nullable List<APIModel> recipes) {
                mRecipesAdapter.setRecipesList(recipes);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_toolbar, menu);

        MenuItem searchItem = menu.findItem(R.id.search_item);

        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search recipes...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mRecipeViewModel.getData(query);
                searchView.clearFocus();

                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;

    }
}

