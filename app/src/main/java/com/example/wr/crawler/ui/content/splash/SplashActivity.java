package com.example.wr.crawler.ui.content.splash;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.wr.crawler.R;
import com.example.wr.crawler.di.module.ActivityModule;
import com.example.wr.crawler.ui.base.BaseActivity;
import com.example.wr.crawler.ui.utils.ActivityNavigator;
import com.example.wr.crawler.ui.utils.AlertDialogHelper;

import javax.inject.Inject;

import butterknife.BindView;
import su.levenetc.android.textsurface.Text;
import su.levenetc.android.textsurface.TextBuilder;
import su.levenetc.android.textsurface.TextSurface;
import su.levenetc.android.textsurface.animations.Delay;
import su.levenetc.android.textsurface.animations.Parallel;
import su.levenetc.android.textsurface.animations.Sequential;
import su.levenetc.android.textsurface.animations.ShapeReveal;
import su.levenetc.android.textsurface.animations.SideCut;
import su.levenetc.android.textsurface.animations.Slide;
import su.levenetc.android.textsurface.animations.TransSurface;
import su.levenetc.android.textsurface.contants.Align;
import su.levenetc.android.textsurface.contants.Side;

public class SplashActivity extends BaseActivity implements SplashContract.View{

    @Inject
    SplashPresenter splashPresenter;

    @BindView(R.id.text_surface)
    TextSurface textSurface;

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
        ActivityNavigator.toMainActivity(this);
        finish();
    }

    @Override
    public void showErrorDialog() {
        AlertDialogHelper.showOKOnlyAlertDialog(this, R.string.splash_connect_err,
                (dialog, which) -> finish());
    }

    @Override
    public void loadSplashImage() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Text textTitle = TextBuilder
                .create("IMAGE CRAWLER")
                .setPaint(paint)
                .setSize(35)
                .setAlpha(0)
                .setColor(Color.BLACK)
                .setPosition(Align.SURFACE_CENTER).build();
        Text textSubTitle = TextBuilder
                .create("magewr@gmail.com")
                .setPaint(paint)
                .setSize(20)
                .setAlpha(0)
                .setColor(Color.BLACK)
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textTitle).build();

        textSurface.play(new Sequential(
                ShapeReveal.create(textTitle, 1000, SideCut.show(Side.LEFT), false),
                Delay.duration(500),
                new Parallel(TransSurface.toCenter(textTitle, 500), Slide.showFrom(Side.TOP, textSubTitle, 500))
        ));
    }
}
