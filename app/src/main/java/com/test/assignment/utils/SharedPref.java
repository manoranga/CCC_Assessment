package com.test.assignment.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private static SharedPref instance;
    private final String HEIGHT = "device_height";
    private final String WIDTH = "device_width";
    private final String IS_FIRST_TIME = "is_first_time";
   private SharedPreferences mSharedPref;
    private SharedPreferences artworkPreference;

    public SharedPref(Context context) {
        init(context);
    }

    public static SharedPref getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPref(context);
        }
        return instance;
    }

    private void init(Context context) {
        if (mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);

        if (artworkPreference == null)
            artworkPreference = context.getSharedPreferences("artworksManager", Context.MODE_PRIVATE);
    }

    public String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public void putHeight(int height) {
        if (height != 0) {
            SharedPreferences.Editor editor = mSharedPref.edit();
            editor.putInt(HEIGHT, height);
            editor.apply();
        }
    }

    public void putWidth(int width) {
        if (width != 0) {
            SharedPreferences.Editor editor = mSharedPref.edit();
            editor.putInt(WIDTH, width);
            editor.apply();
        }
    }

    public int getHeight(int defaultHeight) {
        return mSharedPref.getInt(HEIGHT, defaultHeight);
    }

    public int getWidth(int defaultWidth) {
        return mSharedPref.getInt(WIDTH, defaultWidth);
    }


    public void putIsFirstTime(boolean isFirstTime) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(IS_FIRST_TIME, isFirstTime);
        editor.apply();
    }

    public boolean getIsFirstTime() {
        return mSharedPref.getBoolean(IS_FIRST_TIME, true);
    }

}
