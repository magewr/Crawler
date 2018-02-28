package com.example.wr.crawler.ui.content.main.fragment.grid.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wr.crawler.R;
import com.example.wr.crawler.ui.content.main.fragment.list.recyclerview.ListRecyclerVIewAdapter;
import com.example.wr.crawler.ui.content.main.fragment.list.recyclerview.ListRecyclerViewHolder;

/**
 * Created by loadm on 2018-02-28.
 */

public class GridRecyclerViewAdapter extends ListRecyclerVIewAdapter {

    @Override
    public ListRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_grid, parent, false);
        return new ListRecyclerViewHolder(view);
    }
}
