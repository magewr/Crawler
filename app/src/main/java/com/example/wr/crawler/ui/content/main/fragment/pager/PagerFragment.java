package com.example.wr.crawler.ui.content.main.fragment.pager;

import android.support.v4.view.ViewPager;

import com.example.wr.crawler.R;
import com.example.wr.crawler.di.module.FragmentModule;
import com.example.wr.crawler.ui.base.BaseFragment;
import com.example.wr.crawler.ui.content.main.fragment.pager.adapter.PagerAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by loadm on 2018-02-28.
 */

public class PagerFragment extends BaseFragment implements PagerContract.View {

    @Inject
    PagerPresenter presenter;
    private PagerAdapter adapter;

    @BindView(R.id.pager_viewpager)
    ViewPager viewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager;
    }

    @Override
    protected void initDagger() {
        getApplicationComponent().fragmentComponent(new FragmentModule(this)).inject(this);
    }

    @Override
    protected void initPresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public void initView() {
        adapter = new PagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        presenter.setPagerAdapterModel(adapter);
    }

}
