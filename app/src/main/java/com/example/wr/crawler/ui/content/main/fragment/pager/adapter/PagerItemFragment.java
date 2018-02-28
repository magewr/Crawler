package com.example.wr.crawler.ui.content.main.fragment.pager.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wr.crawler.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by loadm on 2018-02-28.
 */

public class PagerItemFragment extends Fragment {

    private String imageName;
    private String caption;

    static PagerItemFragment newInstance(String imageName, String caption) {
        PagerItemFragment fragment = new PagerItemFragment();
        fragment.imageName = imageName;
        fragment.caption = caption;
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

        textView.setText(caption);
        return view;
    }
}
