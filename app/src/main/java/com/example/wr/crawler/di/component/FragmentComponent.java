package com.example.wr.crawler.di.component;

import com.example.wr.crawler.di.module.FragmentModule;
import com.example.wr.crawler.di.module.PagerItemFragmentModule;
import com.example.wr.crawler.di.scope.PerActivity;
import com.example.wr.crawler.di.scope.PerFragment;
import com.example.wr.crawler.ui.content.main.fragment.grid.GridViewFragment;
import com.example.wr.crawler.ui.content.main.fragment.list.ListViewFragment;
import com.example.wr.crawler.ui.content.main.fragment.pager.PagerFragment;
import com.example.wr.crawler.ui.content.main.fragment.pager.adapter.PagerItemFragment;

import dagger.Subcomponent;

/**
 * Created by loadm on 2018-02-28.
 */

@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(GridViewFragment fragment);
    void inject(ListViewFragment fragment);
    void inject(PagerFragment fragment);
}
