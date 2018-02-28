package com.example.wr.crawler.di.module;

import com.example.wr.crawler.ui.base.BaseFragment;
import com.example.wr.crawler.ui.content.main.fragment.grid.recyclerview.GridRecyclerViewAdapter;

import dagger.Module;

/**
 * Created by loadm on 2018-02-28.
 */

@Module
public class FragmentModule {
    private final BaseFragment fragment;

    public FragmentModule(BaseFragment fragment) {
        this.fragment = fragment;
    }
}
