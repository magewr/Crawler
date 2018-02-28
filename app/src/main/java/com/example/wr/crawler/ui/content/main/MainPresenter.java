package com.example.wr.crawler.ui.content.main;

import com.example.wr.crawler.data.DataRepository;
import com.example.wr.crawler.data.remote.dto.ImageDTO;
import com.example.wr.crawler.interactor.GetSampleDTOUseCase;
import com.example.wr.crawler.ui.base.Presenter;
import com.example.wr.crawler.ui.listener.SimpleCompletableObserver;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by WR on 2017-11-27.
 */

public class MainPresenter extends Presenter<MainContract.View> implements MainContract.Presenter {

    DataRepository dataRepository;

    @Inject
    MainPresenter(DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    @Override
    public void onCreatePresenter() {
        super.onCreatePresenter();
        getView().initView();
    }

}
