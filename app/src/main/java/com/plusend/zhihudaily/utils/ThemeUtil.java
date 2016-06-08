package com.plusend.zhihudaily.utils;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.plusend.zhihudaily.common.Constants;

/**
 * Created by plusend on 16/6/7.
 */
public class ThemeUtil {

    public static void setTheme(Context context, int mode) {
        switch (mode) {
            case AppCompatDelegate.MODE_NIGHT_NO:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            default:
                break;
        }
    }

    public static void setTheme(Context context) {
        int currentNightMode = PreferenceUtil.getInt(context, Constants.NIGHT_MODE,
                AppCompatDelegate.MODE_NIGHT_NO);
        setTheme(context, currentNightMode);
    }
}
