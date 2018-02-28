package com.example.wr.crawler.ui.content.main.fragment.pager.adapter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wr.crawler.App;
import com.example.wr.crawler.R;
import com.example.wr.crawler.di.module.PagerItemFragmentModule;
import com.example.wr.crawler.ui.listener.SimpleSingleObserver;
import com.example.wr.crawler.ui.utils.ImageLoadHelper;
import com.example.wr.crawler.ui.utils.ThumbnailSize;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by loadm on 2018-02-28.
 */

public class PagerItemFragment extends Fragment {

    private String imageName;
    private String caption;
    private Disposable disposable;

    @Inject
    ImageLoadHelper imageLoadHelper;

    static PagerItemFragment newInstance(String imageName, String caption) {
        PagerItemFragment fragment = new PagerItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("imageName", imageName);
        bundle.putString("caption", caption);
        fragment.setArguments(bundle);
        return fragment;
    }


    @BindView(R.id.pager_image)
    ImageView imageView;

    @BindView(R.id.pager_text)
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_item, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        imageName = bundle.getString("imageName");
        caption = bundle.getString("caption");

        App.get(getContext()).getApplicationComponent().pagerItemFragmentComponent(new PagerItemFragmentModule(this)).inject(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        textView.setText(caption);
        Single<Bitmap> single = imageLoadHelper.getBitmapByImageName(imageName, ThumbnailSize.ForPager)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        disposable = single.subscribeWith(new SimpleSingleObserver<Bitmap>() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (disposable != null && disposable.isDisposed() == false)
            disposable.dispose();
    }
}
