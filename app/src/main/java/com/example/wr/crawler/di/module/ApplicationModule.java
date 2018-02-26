package com.example.wr.crawler.di.module;

import android.content.Context;

import com.example.wr.crawler.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by WR on 2017-11-29.
 */

@Singleton
@Module
public class ApplicationModule {
    private final App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    Context provideApplicationContext() {
        return this.application;
    }
}
