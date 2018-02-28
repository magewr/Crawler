package com.example.wr.crawler.di.component;

import com.example.wr.crawler.di.module.PagerItemFragmentModule;
import com.example.wr.crawler.di.scope.PerFragment;
import com.example.wr.crawler.ui.content.main.fragment.pager.adapter.PagerItemFragment;

import dagger.Subcomponent;

/**
 * Created by loadm on 2018-02-28.
 */

@PerFragment
@Subcomponent(modules = PagerItemFragmentModule.class)
public interface PagerItemFragmentComponent {
    void inject(PagerItemFragment fragment);
}
