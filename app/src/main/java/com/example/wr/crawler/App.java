package com.example.wr.crawler;

import android.app.Application;
import android.content.Context;

import com.example.wr.crawler.di.component.ApplicationComponent;
import com.example.wr.crawler.di.component.DaggerApplicationComponent;
import com.example.wr.crawler.di.module.ApiModule;
import com.example.wr.crawler.di.module.ApplicationModule;

import lombok.Getter;

/**
 * Created by WR on 2017-11-27.
 */

public class App extends Application {

    private ApplicationComponent applicationComponent;
    @Getter private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .apiModule(new ApiModule())
                .applicationModule(new ApplicationModule(this))
                .build();
        context = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static App get(Context context) {
        return (App)context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
