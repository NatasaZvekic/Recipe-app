package com.recipe.search.app.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.recipe.search.app.R;
import com.recipe.search.app.adapters.DatabaseAdapter;
import com.recipe.search.app.models.RecipeDatabase;
import com.recipe.search.app.settings.AppPreferenceManager;
import com.recipe.search.app.viewmodels.DatabaseViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InsertRecipe extends AppCompatActivity {
    private Button saveBtn;
    private EditText title, desc;
    private DatabaseViewModel viewModel;
    private ArrayList<RecipeDatabase> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private DatabaseAdapter adapter;
    private Button choose;
    private AppPreferenceManager preferenceManager;
    private Uri filePath;
    private ImageView image;
    private String random;
    private Dialog d;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferenceManager = new AppPreferenceManager(this);
        if (preferenceManager.getDarkModeState()){
            setTheme(R.style.AppThemeDark);
        }else{
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_insert_recipe);

        viewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        fab = findViewById(R.id.addNew);
        recyclerView = findViewById(R.id.lv);
        adapter = new DatabaseAdapter(InsertRecipe.this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        setObserver();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
         });
        }

        public void setObserver(){
            viewModel.getRecipes().observe(this, new Observer<List<RecipeDatabase>>() {
                @Override
                public void onChanged(@Nullable List<RecipeDatabase> recipeDatabases) {
                    adapter.setRecipeList(recipeDatabases);
                }
            });
        }

        public void showDialog()
        {
            d= new Dialog(this);
            d.setContentView(R.layout.input_dialog);
            d.show();

            title = d.findViewById(R.id.titleEditText);
            desc = d.findViewById(R.id.desdEditText);
            saveBtn = d.findViewById(R.id.saveBtn);
            image = d.findViewById(R.id.imageView);
            choose = d.findViewById(R.id.choose);

            choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent();
                    i.setType("image/*");
                    i.setAction(i.ACTION_GET_CONTENT);
                    startActivityForResult(i.createChooser(i, "Select image"), 1);
                }
            });

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(title.getText().length()== 0 || desc.getText().length() == 0) {
                        Toast.makeText(InsertRecipe.this, "Cant leave empty field!", Toast.LENGTH_SHORT).show();
                    }
                    else if(title.getText().length() > 91 || desc.getText().length()>91){
                        Toast.makeText(InsertRecipe.this, "Text must be shorter", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        random = UUID.randomUUID().toString();
                        viewModel.input(title.getText().toString(), desc.getText().toString(), random, filePath);
                        d.dismiss();
                    }
                    filePath = null;
                    }
            });
        }

    @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 1 && resultCode == RESULT_OK  && data != null && data.getData() != null){
                filePath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    image.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}

