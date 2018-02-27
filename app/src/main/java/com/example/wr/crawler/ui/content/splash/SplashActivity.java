package com.example.wr.crawler.ui.content.splash;

import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;

import com.example.wr.crawler.R;
import com.example.wr.crawler.di.module.ActivityModule;
import com.example.wr.crawler.ui.base.BaseActivity;
import com.example.wr.crawler.ui.content.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;

public class SplashActivity extends BaseActivity implements SplashContract.View{

    @Inject
    SplashPresenter splashPresenter;

    @BindView(R.id.imgView)
    ImageView splashImageVIew;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initDagger() {
        getApplicationComponent().activityComponent(new ActivityModule(this)).inject(this);
    }

    @Override
    protected void initPresenter() {
        super.presenter = splashPresenter;
        presenter.setView(this);
    }

    @Override
    public void moveToMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loadSplashImage() {
    }
}
