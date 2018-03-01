package com.example.wr.crawler.data.local;

import android.graphics.Bitmap;

import javax.inject.Inject;

/**
 * Created by WR on 2017-11-29.
 */

public class LocalRepository {

    ImageCacheHelper imageCacheHelper;

    @Inject
    LocalRepository(ImageCacheHelper imageCacheHelper){
        this.imageCacheHelper = imageCacheHelper;
    }

    public void addBitmapToCache(String key, Bitmap bitmap) {
        imageCacheHelper.addBitmapToCache(key, bitmap);
    }

    public Bitmap getBitmapFromCache(String key) {
        return imageCacheHelper.getBitmapFromDiskCache(key);
    }

    public void removeBitmapFromCache(String key) {
        imageCacheHelper.removeBitmapFromCache(key);
    }
}
