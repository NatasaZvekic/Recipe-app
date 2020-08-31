package com.recipe.search.app.models;

public class RecipeDatabase {
    private String title, desc, key, image;

    public  RecipeDatabase(){}

    public String getTitle(){return title;}
    public String getDesc(){return desc;}
    public String getImage(){return  image;}
    public String  getKey(){return key;}

    public void setTitle(String title){this.title=title;}
    public void setDesc(String desc){this.desc=desc;}
    public void setImage(String image){this.image = image;}
    public void setKey (String key){this.key = key;}
}
