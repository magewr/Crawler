package com.example.wr.crawler.ui.listener;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by loadm on 2018-02-27.
 */

public class SimpleSingleObserver<T> implements SingleObserver<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }
}
