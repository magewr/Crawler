package com.example.wr.crawler.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wr.crawler.App;
import com.example.wr.crawler.di.component.ApplicationComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by loadm on 2018-02-28.
 */

public abstract class BaseFragment extends Fragment {

    Unbinder unbinder;
    protected Presenter presenter;

    protected abstract void initDagger();
    protected abstract void initPresenter();
    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(getLayoutId(), null);
        unbinder = ButterKnife.bind(this, view);

        initDagger();
        initPresenter();

        if (presenter != null)
            presenter.onCreatePresenter();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter != null)
            presenter.onStartPresenter();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null)
            presenter.onStopPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected ApplicationComponent getApplicationComponent() {
        return App.get(getContext()).getApplicationComponent();
    }
}
