package com.example.wr.crawler.ui.content.main;

import android.widget.TextView;

import com.example.wr.crawler.R;
import com.example.wr.crawler.data.remote.dto.ImageDTO;
import com.example.wr.crawler.di.component.ActivityComponent;
import com.example.wr.crawler.di.module.ActivityModule;
import com.example.wr.crawler.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by WR on 2017-11-27.
 */

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainPresenter presenter;
    ActivityComponent activityComponent;

    @BindView(R.id.textView3)
    TextView textView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDagger() {
        activityComponent = getApplicationComponent().activityComponent(new ActivityModule(this));
        activityComponent.inject(this);
    }

    @Override
    protected void initPresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public void showSampleData(int size) {
        textView.setText("로딩완료 / count :" + size);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
