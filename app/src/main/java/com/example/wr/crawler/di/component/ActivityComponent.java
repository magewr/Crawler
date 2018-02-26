package com.example.wr.crawler.di.component;

import com.example.wr.crawler.di.module.ActivityModule;
import com.example.wr.crawler.di.scope.PerActivity;
import com.example.wr.crawler.ui.content.main.MainActivity;
import com.example.wr.crawler.ui.content.splash.SplashActivity;

import dagger.Subcomponent;

/**
 * Created by WR on 2017-11-29.
 */

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject (MainActivity activity);

    void inject (SplashActivity activity);
}
