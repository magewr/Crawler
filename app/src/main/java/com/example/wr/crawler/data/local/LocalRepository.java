package com.example.wr.crawler.data.local;

import android.graphics.Bitmap;

import java.io.File;
import java.io.IOException;

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

    public void addBitmapFileToCache(String key, File bitmapFile) {
        imageCacheHelper.addBitmapToCache(key, bitmapFile);
    }

    public Bitmap getBitmapFromCache(String key) {
        return imageCacheHelper.getBitmapFromDiskCache(key);
    }

    public String getCacheDirWithFileName(String fileName) throws IOException{
        return imageCacheHelper.getDiskCacheFileName(fileName);
    }

//    public void removeBitmapFromCache(String key) {
//        imageCacheHelper.removeBitmapFromCache(key);
//    }
}
