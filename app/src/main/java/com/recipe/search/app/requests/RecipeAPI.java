package com.recipe.search.app.requests;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeAPI {

    @GET("search")
    Call<RecipeInfo> getRecipe(@Query("q") String q, @Query("app_id") String appId, @Query("app_key") String appKey);
}
