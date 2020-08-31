package com.recipe.search.app.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.recipe.search.app.R;

public class RecipesViewHolder extends RecyclerView.ViewHolder {

    TextView titleText;
    ImageView image;
    LinearLayout layout;

    public RecipesViewHolder(@NonNull View itemView) {
        super(itemView);

        titleText = itemView.findViewById(R.id.single_text_title);
        image = itemView.findViewById(R.id.single_image);
        layout = itemView.findViewById(R.id.layout);
    }


}
