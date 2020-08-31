package com.recipe.search.app.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.recipe.search.app.R;
import com.recipe.search.app.repositories.DatabaseRepository;
import com.recipe.search.app.settings.AppPreferenceManager;

public class Settings extends AppCompatActivity {

    private AppPreferenceManager manager;
    private CardView cardView;
    private Button upload, choose;
    private ImageView image;
    private Uri filePath;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseRepository repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manager = new AppPreferenceManager(this);
        if (manager.getDarkModeState()){
            setTheme(R.style.AppThemeDark);
        }else{
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_setting);


        cardView = findViewById(R.id.cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                builder.setTitle("Dark Mode")
                        .setMessage("Enabling/Disabling dark mode requires app UI to restart! Do you want to continue?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (manager.getDarkModeState()){
                                    darkMode(false);
                                }else{
                                    darkMode(true);
                                }
                            }
                        }).setNegativeButton("No", null)
                        .create().show();
            }
        });
    }



    private void darkMode(boolean b) {
        manager.setDarkModeState(b);
        Toast.makeText(this, "Changing dark mode!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

}

