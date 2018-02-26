package com.example.wr.crawler.ui.base;

import java.util.concurrent.atomic.AtomicBoolean;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by WR on 2017-11-27.
 */

public abstract class Presenter<T extends BaseView> {

    @Getter @Setter
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

}
