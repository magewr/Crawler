package com.example.wr.crawler.ui.content.main.fragment.list;

import com.example.wr.crawler.data.DataRepository;
import com.example.wr.crawler.ui.base.Presenter;
import com.example.wr.crawler.ui.content.main.fragment.list.recyclerview.ListRecyclerVIewAdapterModel;

import javax.inject.Inject;

/**
 * Created by loadm on 2018-02-28.
 */

public class ListViewPresenter extends Presenter<ListViewContract.View> implements ListViewContract.Presenter {

    DataRepository dataRepository;
    ListRecyclerVIewAdapterModel adapterModel;

    @Inject
    ListViewPresenter(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void onCreatePresenter() {
        super.onCreatePresenter();
        getView().initView();
    }

    @Override
    public void setAdapterModel(ListRecyclerVIewAdapterModel adapterModel) {
        this.adapterModel = adapterModel;
        adapterModel.setImageDTOList(dataRepository.getImageDtoList());
    }
}
