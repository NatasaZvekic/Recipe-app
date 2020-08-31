package com.recipe.search.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.recipe.search.app.R;
import com.recipe.search.app.activities.FindRecipes;
import com.recipe.search.app.activities.InsertRecipe;
import com.recipe.search.app.activities.Settings;
import com.recipe.search.app.settings.AppPreferenceManager;

public class MainActivity extends AppCompatActivity {


    private FloatingActionButton settings;
    private Button find_recipes;
    private FloatingActionButton fab;
    private AppPreferenceManager preferenceManager;
    private ImageView find, my;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        preferenceManager = new AppPreferenceManager(this);
        if (preferenceManager.getDarkModeState()) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_main);

        find = findViewById(R.id.find_recipe12);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, FindRecipes.class));
            }
        });


        my = findViewById(R.id.my_recipe);
        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, InsertRecipe.class));

            }
        });


        settings = findViewById(R.id.fab);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Settings.class));

            }
        });


        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }


}
