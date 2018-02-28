package com.example.wr.crawler.ui.content.main.fragment.pager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.wr.crawler.data.remote.dto.ImageDTO;

import java.util.ArrayList;

/**
 * Created by loadm on 2018-02-28.
 */

public class PagerAdapter extends FragmentStatePagerAdapter implements PagerAdapterModel{

    private ArrayList<ImageDTO> imageList;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ImageDTO imageDto = imageList.get(position);
        return PagerItemFragment.newInstance(imageDto.getImgSrc(), imageDto.getCaption());
    }

    @Override
    public int getCount() {
        if (imageList == null)
            return 0;
        return imageList.size();
    }

    @Override
    public void setImageDTOList(ArrayList<ImageDTO> list) {
        imageList = list;
        notifyDataSetChanged();
    }

    @Override
    public void refreshDataSet() {
        notifyDataSetChanged();
    }

}
