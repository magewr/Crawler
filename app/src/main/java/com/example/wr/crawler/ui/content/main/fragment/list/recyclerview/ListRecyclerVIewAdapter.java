package com.example.wr.crawler.ui.content.main.fragment.list.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wr.crawler.R;
import com.example.wr.crawler.data.remote.dto.ImageDTO;

import java.util.ArrayList;

/**
 * Created by loadm on 2018-02-28.
 */

public class ListRecyclerVIewAdapter extends RecyclerView.Adapter<ListRecyclerViewHolder> implements  ListRecyclerVIewAdapterModel{

    private ArrayList<ImageDTO> imageList;

    @Override
    public ListRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list, parent, false);
        return new ListRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListRecyclerViewHolder holder, int position) {
        holder.textView.setText(imageList.get(position).getCaption());
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
}
