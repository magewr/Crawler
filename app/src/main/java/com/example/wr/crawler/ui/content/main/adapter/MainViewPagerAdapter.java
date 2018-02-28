package com.example.wr.crawler.ui.content.main.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.wr.crawler.ui.content.main.fragment.grid.GridViewFragment;
import com.example.wr.crawler.ui.content.main.fragment.list.ListViewFragment;
import com.example.wr.crawler.ui.content.main.fragment.pager.PagerFragment;

/**
 * Created by loadm on 2018-02-28.
 */
public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ListViewFragment();
            case 1:
                return new GridViewFragment();
            case 2:
                return new PagerFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }


}
