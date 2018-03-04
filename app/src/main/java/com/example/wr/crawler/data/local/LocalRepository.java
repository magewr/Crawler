package com.example.wr.crawler.data.local;

import android.graphics.Bitmap;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by WR on 2017-11-29.
 */

public class LocalRepository {

    ImageCache imageCache;

    @Inject
    LocalRepository(ImageCache imageCache){
        this.imageCache = imageCache;
    }

    public void addBitmapFileToCache(String key, File bitmapFile) {
        imageCache.addBitmapToCache(key, bitmapFile);
    }

    public File getBitmapFileFromCache(String key) {
        return imageCache.getBitmapFileFromDiskCache(key);
    }

    public String getCacheDirWithFileName(String fileName) throws IOException{
        return imageCache.getDiskCacheFileName(fileName);
    }

}
