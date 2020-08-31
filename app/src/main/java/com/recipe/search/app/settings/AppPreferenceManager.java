package com.recipe.search.app.settings;

import android.content.Context;
import android.content.SharedPreferences;


public class AppPreferenceManager {
    private Context c;
    private SharedPreferences s;

    public AppPreferenceManager(Context c)
    {
        this.c=c;
        s=c.getSharedPreferences("APP_PREFERENCE", Context.MODE_PRIVATE);
    }

    public void setDarkModeState(boolean enable) {
        SharedPreferences.Editor editor = s.edit();
        editor.putBoolean("darkMode", enable);
        editor.apply();
    }

    public boolean getDarkModeState(){
        return s.getBoolean("darkMode", false);
    }

}
