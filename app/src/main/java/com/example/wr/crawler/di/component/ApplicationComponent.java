package com.example.wr.crawler.di.component;

import com.example.wr.crawler.di.module.ActivityModule;
import com.example.wr.crawler.di.module.ApiModule;
import com.example.wr.crawler.di.module.ApplicationModule;
import com.example.wr.crawler.di.module.FragmentModule;
import com.example.wr.crawler.ui.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by WR on 2017-11-29.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    ActivityComponent activityComponent(ActivityModule activityModule);
    FragmentComponent fragmentComponent(FragmentModule fragmentModule);
}
