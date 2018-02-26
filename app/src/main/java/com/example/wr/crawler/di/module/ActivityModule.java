package com.example.wr.crawler.di.module;

import android.content.Context;

import com.example.wr.crawler.di.scope.PerActivity;
import com.example.wr.crawler.ui.base.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by WR on 2017-11-29.
 */

@Module
public class ActivityModule {
    private final BaseActivity activity;

    public ActivityModule (BaseActivity activity){
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Context provideContext() {
        return activity;
    }

}
