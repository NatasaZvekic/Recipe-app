package com.recipe.search.app.requests;

import com.recipe.search.app.requests.hits.Hits;

import java.util.ArrayList;


public class RecipeInfo {

    private ArrayList<Hits> hits; //json array

    public ArrayList<Hits> getHits() {
        return hits;
    }

}
