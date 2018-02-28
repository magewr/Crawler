package com.example.wr.crawler.ui.content.main.fragment.list;

import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wr.crawler.R;
import com.example.wr.crawler.di.module.FragmentModule;
import com.example.wr.crawler.ui.base.BaseFragment;
import com.example.wr.crawler.ui.content.main.fragment.list.recyclerview.ListRecyclerVIewAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by loadm on 2018-02-28.
 */

public class ListViewFragment extends BaseFragment implements ListViewContract.View {

    @Inject
    protected ListViewPresenter presenter;

    @Nullable @BindView(R.id.list_recyclerview)
    protected RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
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
        ListRecyclerVIewAdapter adapter = new ListRecyclerVIewAdapter();
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                layoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
        presenter.setAdapterModel(adapter);
    }

}
