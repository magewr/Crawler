package com.example.wr.crawler.data.local;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.wr.crawler.App;
import com.example.wr.crawler.ui.listener.SimpleCompletableObserver;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;

/**
 * Created by loadm on 2018-03-01.
 */

@Singleton
public class ImageCacheHelper {
    private final Context context = App.getContext();
    private final LinkedHashMap<String, String> cacheMap = new LinkedHashMap<>();
    private final Object diskCacheLock = new Object();
    @Getter  private boolean diskCacheReady = false;
    private volatile long cacheSize = 0;

    private static final String DISK_CACHE_SUB_DIR = "thumbnails";
    private final long DISK_CACHE_SIZE = 1024 * 1024 * 5; //5MB


    @Inject
    ImageCacheHelper() {
        Completable init = Completable.create(emitter -> {
            synchronized (diskCacheLock) {
                File cacheDir = getDiskCacheDir();
                if (cacheDir.exists() == false)
                    cacheDir.mkdir();
                File[] cachedFiles = cacheDir.listFiles();
                for (int i = 0; i < cachedFiles.length; i++) {
                    File item = cachedFiles[i];
                    cacheMap.put(item.getName(), item.getAbsolutePath());
                    cacheSize += item.length();
                }
                Log.d("ImageCacheHelper", "ImageCache Ready, count :" + cachedFiles.length);
                diskCacheReady = true;
                diskCacheLock.notifyAll();
                emitter.onComplete();
            }
        });
        init.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new SimpleCompletableObserver() {
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "디스크캐시 생성 실패 : " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
            });
    }

    private void removeFirstCachedFile() {
        synchronized (diskCacheLock) {
            Map.Entry<String, String> firstItem = cacheMap.entrySet().iterator().next();
            File target = new File(firstItem.getValue());
            long fileSize = target.length();
            if (target.exists())
                target.delete();

            cacheSize -= fileSize;
            cacheMap.remove(firstItem.getKey());
            Log.d("ImageCacheHelper", "Remove Success, currentCacheSize=" + cacheSize);
        }
    }

    //이미지 캐시에 저장
    public void addBitmapToCache(String key, File imageFile) {

        while (cacheSize > DISK_CACHE_SIZE)
            removeFirstCachedFile();

        synchronized (diskCacheLock) {
            cacheMap.put(key, imageFile.getAbsolutePath());
            cacheSize += imageFile.length();
            Log.d("ImageCacheHelper", "add Success, currentCacheSize=" + cacheSize);
        }
    }

    public Bitmap getBitmapFromDiskCache(String key) {
        Log.d("ImageCacheHelper", "Cache HIT!, KEY=" + key);
        if (cacheMap.containsKey(key)) {
            Bitmap bitmap = BitmapFactory.decodeFile(cacheMap.get(key));
            return bitmap;
        }
        else
            return null;
    }

    public String getDiskCacheFileName(String fileName) throws IOException{
        File target = new File(getDiskCacheDir().getPath() + File.separator + fileName);
        if (!target.exists())
            target.createNewFile();
        return target.getPath();
    }

    public File getDiskCacheDir() {
        Context context = App.getContext();
        final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !isExternalStorageRemovable() ? getExternalCacheDir(context).getPath() : context.getCacheDir()
                .getPath();
        return new File(cachePath + File.separator + DISK_CACHE_SUB_DIR);
    }

    public boolean isExternalStorageRemovable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD)
            return Environment.isExternalStorageRemovable();

        return true;
    }

    public File getExternalCacheDir(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO)
            return context.getExternalCacheDir();

        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";

        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }
}



