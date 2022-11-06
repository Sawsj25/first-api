package com.example.testgradle;

import android.content.Context;
import android.content.SharedPreferences;

public class Spref {

    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    static SharedPreferences sharedPreferences;

    public static SharedPreferences getSharedPreferences(Context context) {
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences(MainActivity.TAG, Context.MODE_PRIVATE);
        return sharedPreferences;
    }
}

