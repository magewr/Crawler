package com.example.wr.crawler.ui.content.main;

import com.example.wr.crawler.data.remote.dto.SampleDTO;
import com.example.wr.crawler.ui.base.BaseView;

/**
 * Created by WR on 2017-11-27.
 */

public interface MainContract{

    interface View extends BaseView {
        void showSampleData(SampleDTO sampleDTO);
    }

    interface Presenter {
        void getSampleData();
        void dispose();
    }
}
