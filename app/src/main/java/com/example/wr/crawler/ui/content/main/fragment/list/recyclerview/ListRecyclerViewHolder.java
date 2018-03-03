package com.example.wr.crawler.ui.content.main.fragment.list.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wr.crawler.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * Created by loadm on 2018-02-28.
 */

public class ListRecyclerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_image)
    ImageView imageView;

    @BindView(R.id.item_text)
    TextView textView;

    private Disposable disposable;

    public ListRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public Disposable getDisposable() {
        return disposable;
    }

    public void setDisposable(Disposable disposable) {
        this.disposable = disposable;
    }
}
