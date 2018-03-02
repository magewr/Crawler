package com.example.wr.crawler.ui.content.main;

import com.example.wr.crawler.data.DataRepository;
import com.example.wr.crawler.ui.base.Presenter;

import javax.inject.Inject;

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
