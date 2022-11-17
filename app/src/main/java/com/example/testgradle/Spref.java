package com.example.testgradle;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

public class Spref {

    private static SharedPreferences sharedPreferences;

    public static SharedPreferences getSharedPreferences(Context context) {
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences(MainActivity.TAG, Context.MODE_PRIVATE);
        return sharedPreferences;
    }
}



