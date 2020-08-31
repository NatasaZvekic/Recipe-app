package com.recipe.search.app.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.recipe.search.app.R;
import com.recipe.search.app.models.RecipeDatabase;
import com.recipe.search.app.repositories.DatabaseRepository;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context c;
    private List<RecipeDatabase> recipe;
    public DatabaseRepository repos;
    private AlertDialog.Builder alertBox;
    private View v;

    public DatabaseAdapter(Context c, ArrayList<RecipeDatabase> recipe) {
        this.c = c;
        this.recipe = recipe;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
         v = (LayoutInflater.from(c).inflate(R.layout.cardview, parent, false));
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Picasso.get()
                .load(recipe.get(position).getImage())
                .placeholder(R.drawable.nophoto)
                .into(((MyViewHolder)holder).imageView);
        ((MyViewHolder)holder).title.setText(recipe.get(position).getTitle());
        ((MyViewHolder)holder).desc.setText(recipe.get(position).getDesc());

        ((MyViewHolder)holder).layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                repos = new DatabaseRepository();
                AlertDialog.Builder alterBox = new AlertDialog.Builder(view.getRootView().getContext()).setTitle("Delete")
                        .setMessage("Are you sure you want to delete recipe?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                repos.deleteItem(recipe.get(position).getKey());
                            }
                        })
                        .setNegativeButton(android.R.string.no, null);

                alterBox.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipe.size();
    }



    public void setRecipeList(List<RecipeDatabase> recipeDatabases) {
        recipe = recipeDatabases;
        notifyDataSetChanged();

    }




    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView title, desc;
        ImageView imageView;
        LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            title = itemView.findViewById(R.id.titleTextView);
            desc = itemView.findViewById(R.id.descTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}