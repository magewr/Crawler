package com.example.wr.crawler.di.module;

import com.example.wr.crawler.ui.content.main.fragment.pager.adapter.PagerItemFragment;

import dagger.Module;

/**
 * Created by loadm on 2018-02-28.
 */

@Module
public class PagerItemFragmentModule {
    private final PagerItemFragment fragment;

    public PagerItemFragmentModule(PagerItemFragment fragment) {
        this.fragment = fragment;
    }
}
