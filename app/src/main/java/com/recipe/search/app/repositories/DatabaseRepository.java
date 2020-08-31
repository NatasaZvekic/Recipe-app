package com.recipe.search.app.repositories;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.recipe.search.app.models.RecipeDatabase;

import java.util.ArrayList;
import java.util.List;


public class DatabaseRepository {
    private DatabaseReference ref;
    public static DatabaseRepository instance;
    private MediatorLiveData<List<RecipeDatabase>> myRecipes = new MediatorLiveData<>();
    private ValueEventListener value;
    private List<RecipeDatabase> list = new ArrayList<>();
    private StorageReference reference;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    public static DatabaseRepository getInstance() {
        if (instance == null) {
            instance = new DatabaseRepository();
        }
        return instance;
    }

    public LiveData<List<RecipeDatabase>> getRecipes() {
        ref = FirebaseDatabase.getInstance().getReference().child("RecipeDatabase");
        value = ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    RecipeDatabase db = ds.getValue(RecipeDatabase.class);
                    db.setKey(ds.getKey());

                    list.add(db);
                }
                myRecipes.postValue(list);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
        return myRecipes;
    }

    public void deleteItem(final String key) {
        ref = FirebaseDatabase.getInstance().getReference().child("RecipeDatabase");
        value = ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                    RecipeDatabase db = ds.getValue(RecipeDatabase.class);
                    if(ds.getKey() == key)
                    {
                        ref.child(ds.getKey()).removeValue();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    public void insertData(String title, String desc, String imageUrl) {
        RecipeDatabase b = new RecipeDatabase();
        b.setTitle(title);
        b.setDesc(desc);
        b.setImage(imageUrl);

        ref.push().setValue(b);
    }

    public void input(final String title, final String desc, String random, final Uri filePath) {

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

            if (filePath != null) {
                reference = storageReference.child("images/" + random);
                reference.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                insertData(title, desc, uri.toString());
                            }
                        });
                    }
                });
            }
            else {
                insertData(title, desc, "https://thumbs.dreamstime.com/z/no-image-available-icon-flat-vector-no-image-available-icon-flat-vector-illustration-132484366.jpg");
            }

        }
}

