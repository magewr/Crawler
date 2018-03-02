package com.example.wr.crawler.ui.content.main;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.wr.crawler.R;
import com.example.wr.crawler.di.component.ActivityComponent;
import com.example.wr.crawler.di.module.ActivityModule;
import com.example.wr.crawler.ui.base.BaseActivity;
import com.example.wr.crawler.ui.content.main.adapter.MainViewPagerAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by WR on 2017-11-27.
 */

public class MainActivity extends BaseActivity implements MainContract.View {

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Inject
    MainPresenter presenter;
    ActivityComponent activityComponent;

    @BindView(R.id.view_pager)  ViewPager viewPager;
    @BindView(R.id.tab_layout)  TabLayout tabLayout;

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
    public void initView() {
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        int[] iconRes = {R.drawable.list, R.drawable.grid, R.drawable.viewpager};
        for (int i = 0 ; i < iconRes.length ; i ++)
            tabLayout.getTabAt(i).setIcon(iconRes[i]);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
