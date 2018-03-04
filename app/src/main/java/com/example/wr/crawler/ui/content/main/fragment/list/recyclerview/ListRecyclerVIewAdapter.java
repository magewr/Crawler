package com.example.wr.crawler.ui.content.main.fragment.list.recyclerview;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wr.crawler.R;
import com.example.wr.crawler.data.remote.dto.ImageDTO;
import com.example.wr.crawler.ui.listener.SimpleSingleObserver;
import com.example.wr.crawler.ui.utils.ImageLoadHelper;
import com.example.wr.crawler.ui.utils.ThumbnailSize;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by loadm on 2018-02-28.
 */

public class ListRecyclerVIewAdapter extends RecyclerView.Adapter<ListRecyclerViewHolder> implements  ListRecyclerVIewAdapterModel{

    private ArrayList<ImageDTO> imageList;
    protected ImageLoadHelper imageLoadHelper;

    @Inject
    protected ListRecyclerVIewAdapter(ImageLoadHelper imageLoadHelper) {
        this.imageLoadHelper = imageLoadHelper;
    }

    @Override
    public ListRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list, parent, false);
        return new ListRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListRecyclerViewHolder holder, int position) {
        holder.textView.setText(imageList.get(position).getCaption());
        holder.imageView.setImageResource(R.drawable.loading_drawable);
        AnimationDrawable animation = (AnimationDrawable)holder.imageView.getDrawable();
        animation.start();
        Single<Bitmap> single = imageLoadHelper.getBitmapByImageName(imageList.get(position).getImgSrc(), getThumbnailSize())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        holder.setDisposable(single.subscribeWith(new SimpleSingleObserver<Bitmap>() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                holder.imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onError(Throwable e) {
                holder.imageView.setImageResource(R.drawable.broken);
            }
        }));

    }

    @Override
    public void onViewRecycled(ListRecyclerViewHolder holder) {
        if(holder.getDisposable().isDisposed() == false) {
            holder.getDisposable().dispose();
        }
    }

    @Override
    public int getItemCount() {
        if (imageList == null)
            return 0;
        return imageList.size();
    }

    @Override
    public void setImageDTOList(ArrayList<ImageDTO> list) {
        imageList = list;
        notifyDataSetChanged();
    }

    protected ThumbnailSize getThumbnailSize() {
        return ThumbnailSize.ForListView;
    }
}
