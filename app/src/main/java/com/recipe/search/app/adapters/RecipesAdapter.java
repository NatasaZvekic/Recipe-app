package com.recipe.search.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.recipe.search.app.R;
import com.recipe.search.app.models.APIModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecipesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<APIModel> recipesList;
    private OnItemClickListener listener;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_recipe_view, viewGroup, false);
      return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Picasso.get()
                .load(recipesList.get(i).getImage_url())
                .placeholder(R.drawable.ic_launcher_background)
                .into(((RecipesViewHolder)viewHolder).image);
        ((RecipesViewHolder)viewHolder).titleText.setText(recipesList.get(i).getTitle());
        ((RecipesViewHolder)viewHolder).layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onClickListener(recipesList.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (recipesList != null) {
            return recipesList.size();
        }
        return 0;
    }

    public void setRecipesList(List<APIModel> recipes){
        recipesList = recipes;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
