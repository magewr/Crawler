package com.example.wr.crawler.ui.content.splash;

import com.example.wr.crawler.ui.base.BaseView;

/**
 * Created by WR on 2017-11-27.
 */

public interface SplashContract{
    interface View extends BaseView {
        void moveToMainActivity();
        void loadSplashImage();
    }

    interface Presenter {

    }
}
