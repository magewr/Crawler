package com.example.wr.crawler.ui.utils;

import android.content.Context;

import com.example.wr.crawler.ui.content.main.MainActivity;

/**
 * Created by loadm on 2018-03-02.
 */

public class ActivityNavigator {

    private ActivityNavigator(){}

    public static void toMainActivity(Context context) {
        context.startActivity(MainActivity.getCallingIntent(context));
    }
}
