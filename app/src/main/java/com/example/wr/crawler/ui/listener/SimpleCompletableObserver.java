package com.example.wr.crawler.ui.listener;

import android.widget.Toast;

import com.example.wr.crawler.App;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;

/**
 * Created by loadm on 2018-02-27.
 */

public class SimpleCompletableObserver extends DisposableCompletableObserver {

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(App.getContext(), "에러발생 : " + e.getMessage(), Toast.LENGTH_LONG).show();
    }
}
