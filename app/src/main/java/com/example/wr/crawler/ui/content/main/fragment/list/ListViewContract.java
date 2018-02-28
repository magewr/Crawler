package com.example.wr.crawler.ui.content.main.fragment.list;

import com.example.wr.crawler.ui.base.BaseView;
import com.example.wr.crawler.ui.content.main.fragment.list.recyclerview.ListRecyclerVIewAdapter;
import com.example.wr.crawler.ui.content.main.fragment.list.recyclerview.ListRecyclerVIewAdapterModel;

/**
 * Created by loadm on 2018-02-28.
 */

public interface ListViewContract {

    interface View extends BaseView {
        void initView();
    }

    interface Presenter {
        void setAdapterModel(ListRecyclerVIewAdapterModel adapterModel);
    }
}
