package com.example.wr.crawler.ui.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.DisplayMetrics;

import com.example.wr.crawler.App;
import com.example.wr.crawler.data.DataRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by loadm on 2018-02-28.
 */

@Singleton
public class ImageLoadHelper {

    DataRepository dataRepository;

    @Inject
    ImageLoadHelper(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public Single<Bitmap> getBitmapByImageName(String imageName, ThumbnailSize thumbnailSize) {
        Single<Bitmap> single = dataRepository.getImageByName(imageName)
                .map(bitmap -> compressBitmap(bitmap, thumbnailSize));

        return single;
    }

    private Bitmap compressBitmap(Bitmap origin, ThumbnailSize thumbnailSize) {
        int width = origin.getWidth();
        int height = origin.getHeight();
        float newWidth = 0;
        float newHeight = 0;
        switch (thumbnailSize) {
            case ForListView:
                newHeight = convertDpToPixel(60.0f);
                newWidth = convertDpToPixel(60.0f);
                break;
            case ForGridView:
                newHeight = convertDpToPixel(100.0f);
                newWidth = convertDpToPixel(100.0f);
                break;
            case ForPager:
                return origin;
        }
        float scaleWidth = newWidth / width;
        float scaleHeight = newHeight / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

    private float convertDpToPixel(float dp){
        Resources resources = App.getContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }
}
