package com.example.wr.crawler.ui.base;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by WR on 2017-11-27.
 */

public abstract class Presenter<T extends BaseView> {

    private T view;

    protected AtomicBoolean isViewAlive = new AtomicBoolean();

    public void onCreatePresenter() {
    }

    public void onStartPresenter() {
        isViewAlive.set(true);
    }

    public void onStopPresenter() {
        isViewAlive.set(false);
    }

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

}
