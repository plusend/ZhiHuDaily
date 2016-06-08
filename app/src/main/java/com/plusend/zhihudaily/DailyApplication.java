package com.plusend.zhihudaily;

import android.app.Application;

import com.plusend.zhihudaily.utils.ThemeUtil;

/**
 * Created by plusend on 16/6/7.
 */
public class DailyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ThemeUtil.setTheme(getApplicationContext());
    }

}
