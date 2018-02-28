package com.example.wr.crawler.ui.content.main.fragment.pager;

import com.example.wr.crawler.data.DataRepository;
import com.example.wr.crawler.ui.base.Presenter;
import com.example.wr.crawler.ui.content.main.fragment.pager.adapter.PagerAdapterModel;

import javax.inject.Inject;

/**
 * Created by loadm on 2018-02-28.
 */

public class PagerPresenter extends Presenter<PagerContract.View> implements PagerContract.Presenter {
    DataRepository dataRepository;
    PagerAdapterModel pagerAdapterModel;

    @Inject
    PagerPresenter(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void onCreatePresenter() {
        super.onCreatePresenter();
        getView().initView();
    }

    @Override
    public void setPagerAdapterModel(PagerAdapterModel pagerAdapterModel) {
        this.pagerAdapterModel = pagerAdapterModel;
        this.pagerAdapterModel.setImageDTOList(dataRepository.getImageDtoList());
    }
}
