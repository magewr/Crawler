package com.example.wr.crawler.ui.content.main.fragment.pager;

import com.example.wr.crawler.ui.base.BaseView;
import com.example.wr.crawler.ui.content.main.fragment.pager.adapter.PagerAdapterModel;

/**
 * Created by loadm on 2018-02-28.
 */

public interface PagerContract {
    interface View extends BaseView {
        void initView();
    }

    interface Presenter {
        void setPagerAdapterModel(PagerAdapterModel pagerAdapterModel);
    }
}
