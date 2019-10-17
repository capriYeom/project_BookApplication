package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {
    private static final String PREFERENCE_KEY_SEARCH_HISTORY = "history";

    private static Context mAppContext;

    private PreferenceUtils() {
    }

    private static SharedPreferences getSharedPreferences() {
        return mAppContext.getSharedPreferences("BookApp", Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        mAppContext = context;
    }

    public static void setHistory(String keyword) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PREFERENCE_KEY_SEARCH_HISTORY, keyword).apply();
    }

    public static String getHistory() {
        return getSharedPreferences().getString(PREFERENCE_KEY_SEARCH_HISTORY, "");
    }

    public static void clearAll() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear().apply();
    }
}
