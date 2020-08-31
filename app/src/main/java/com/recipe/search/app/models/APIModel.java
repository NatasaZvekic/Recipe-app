package com.recipe.search.app.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class APIModel implements Parcelable {

    //Data
    @SerializedName("label")
    private String title;

    @SerializedName("image")
    private String image_url;

    private String url;

    private String[] dietLabels;

    @SerializedName("yield")
    private int numberOfServings;

    private String[] healthLabels;

    private String[] cautions;

    @SerializedName("ingredientLines") //to znaci da tamo pise ovako
    private String[] ingredients; //a ja hocu ovako da se zove

    private double calories;

    @SerializedName("totalTime")
    private int time;

    public APIModel(Parcel in) {
        title = in.readString();
        image_url = in.readString();
        url = in.readString();
        dietLabels = in.createStringArray();
        numberOfServings = in.readInt();
        healthLabels = in.createStringArray();
        cautions = in.createStringArray();
        ingredients = in.createStringArray();
        calories = in.readDouble();
        time = in.readInt();
    }

    //GETTERS
    public int getNumberOfServings() {
        return numberOfServings;
    }

    public String[] getHealthLabels() {
        return healthLabels;
    }

    public String[] getCautions() {
        return cautions;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public double getCalories() {
        return calories;
    }

    public int getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }

    public String[] getDietLabels() {
        return dietLabels;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    public void setCalories(int calories) {this.calories = calories;}


    public static final Creator CREATOR =
            new Creator() {
                public APIModel createFromParcel(Parcel in) {
                    return new APIModel(in);
                }

                public APIModel[] newArray(int size) {
                    return new APIModel[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(image_url);
        dest.writeString(url);
        dest.writeStringArray(dietLabels);
        dest.writeInt(numberOfServings);
        dest.writeStringArray(healthLabels);
        dest.writeStringArray(cautions);
        dest.writeStringArray(ingredients);
        dest.writeDouble(calories);
        dest.writeInt(time);
    }
}
