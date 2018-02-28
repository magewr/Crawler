package com.example.wr.crawler.ui.content.main.fragment.grid;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wr.crawler.R;
import com.example.wr.crawler.di.module.FragmentModule;
import com.example.wr.crawler.ui.base.BaseFragment;
import com.example.wr.crawler.ui.content.main.fragment.grid.recyclerview.GridRecyclerViewAdapter;
import com.example.wr.crawler.ui.content.main.fragment.list.ListViewFragment;
import com.example.wr.crawler.ui.content.main.fragment.list.recyclerview.ListRecyclerVIewAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by loadm on 2018-02-28.
 */

public class GridViewFragment extends ListViewFragment {

    @BindView(R.id.grid_recyclerview)
    protected RecyclerView recyclerView;

    @Inject
    protected GridRecyclerViewAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_grid;
    }

    @Override
    protected void initDagger() {
        getApplicationComponent().fragmentComponent(new FragmentModule(this)).inject(this);
    }

    @Override
    public void initView() {
        super.adapter = adapter;
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        presenter.setAdapterModel(adapter);
    }
}
