package com.example.wr.crawler.ui.content.splash;

import com.example.wr.crawler.data.DataRepository;
import com.example.wr.crawler.ui.base.Presenter;
import com.example.wr.crawler.ui.listener.SimpleCompletableObserver;
import com.example.wr.crawler.ui.utils.AlertDialogHelper;

import javax.inject.Inject;

/**
 * Created by WR on 2017-11-27.
 */

public class SplashPresenter extends Presenter<SplashContract.View> implements SplashContract.Presenter {

    DataRepository dataRepository;

    @Inject
    public SplashPresenter(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void onCreatePresenter() {
        super.onCreatePresenter();
        getView().loadSplashImage();
        getImageList();
    }

    @Override
    public void getImageList() {
        dataRepository.getImageListItem().subscribe(new SimpleCompletableObserver() {
            @Override
            public void onComplete() {
                getView().moveToMainActivity();
            }

            @Override
            public void onError(Throwable e) {
                getView().showErrorDialog();
            }
        });
    }
}
