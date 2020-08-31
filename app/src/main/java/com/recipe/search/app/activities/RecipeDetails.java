package com.recipe.search.app.activities;

import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.recipe.search.app.R;
import com.recipe.search.app.models.APIModel;
import com.recipe.search.app.settings.AppPreferenceManager;
import com.squareup.picasso.Picasso;

public class RecipeDetails extends AppCompatActivity {

    private TextView title;
    private TextView numberOfServings;
    private TextView time;
    private TextView calories;
    private TextView ingredients;
    private TextView source;
    private TextView cautions;
    private TextView health;
    private TextView diet;
    private ImageView image;
    private ImageView mImage;
    private FloatingActionButton mUrlButton;
    private TextView mTitle;
    private TextView mCalories;
    private TextView mSource;
    private TextView mNumberOfServings;
    private TextView mTime;
    private TextView mIngredients;
    private TextView mDiet;
    private TextView mHealth;
    private TextView mCautions;
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

            setContentView(R.layout.activity_recipe_details);
            mTitle = findViewById(R.id.titleTextView);
            mImage = findViewById(R.id.imageView);
            mUrlButton = findViewById(R.id.floatingActionButton);
            mCalories = findViewById(R.id.caloriesTextView);
            mNumberOfServings = findViewById(R.id.numberOfServingsTextView);
            mTime = findViewById(R.id.timeTextView);
            mIngredients = findViewById(R.id.ingredientsTextView);
            mDiet = findViewById(R.id.dietTextView);
            mHealth = findViewById(R.id.healthTextView);
            mCautions = findViewById(R.id.cautionsTextView);
            getPassedIntent();
        }

        private void getPassedIntent() {
            if(getIntent().hasExtra("recipe")){
                APIModel recipe = getIntent().getParcelableExtra("recipe");
                setProperties(recipe);
            }
        }

        private void setProperties(APIModel recipe) {
            mTitle.setText(recipe.getTitle());
            Picasso.get() //link ucita u sliku
                    .load(recipe.getImage_url())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(mImage);
            int calories = (int) recipe.getCalories();
            String cal = "Calories:\n" + String.valueOf(calories);
            mCalories.setText(cal);
            mNumberOfServings.setText(String.valueOf(recipe.getNumberOfServings()));
            if(recipe.getTime() != 0) {
                String time = "Time: \n" + String.valueOf(recipe.getTime());
                mTime.setText(time);
            }
            StringBuilder stringBuilder = new StringBuilder();
            for(String s : recipe.getIngredients()){
                stringBuilder.append("• " + s + "\n");
            }
            mIngredients.setText(stringBuilder.toString());

            if(recipe.getHealthLabels().length > 0) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("HEALTH:");
                for (String s : recipe.getHealthLabels()) {

                    stringBuilder2.append("\n" + s);
                }
                mHealth.setText(stringBuilder2.toString());
            }

            if(recipe.getDietLabels().length > 0) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("DIET:");
                for (String s : recipe.getDietLabels()) {
                    stringBuilder3.append("\n" + s);
                }
                mDiet.setText(stringBuilder3.toString());
            }

            if(recipe.getCautions().length > 0) {
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append("CAUTIONS:");
                for (String s : recipe.getCautions()) {
                    stringBuilder4.append("\n" + s);
                }
                mCautions.setText(stringBuilder4.toString());
            }
        }

}
