package com.plusend.zhihudaily.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.plusend.zhihudaily.common.Constants;

/**
 * Created by plusend on 16/6/7.
 */
public class PreferenceUtil {

    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(
                Constants.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static int getInt(Context context, String key, int value) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences(
                Constants.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(key, value);
    }
}
