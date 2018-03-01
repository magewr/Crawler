package com.example.wr.crawler.data.local;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.Toast;

import com.example.wr.crawler.App;
import com.example.wr.crawler.ui.listener.SimpleCompletableObserver;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by loadm on 2018-03-01.
 */

@Singleton
public class ImageCacheHelper {
    private final Context context = App.getContext();
//    private LruCache<String, Bitmap> memoryCache;
    private DiskLruCache diskLruCache;
    private final Object diskCacheLock = new Object();
    private boolean diskCacheStarting = true;
    private final String DISK_CACHE_SUBDIR = "thumnails";
    private final int IMAGE_QUALITY = 70;
    private final int DISK_CACHE_SIZE = 1024 * 1024 * 10; //10MB

    @Inject
    ImageCacheHelper() {
        final int cacheSize = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        int maxSize = 1024 * 1024 * cacheSize / 4; //앱 메모리의 1/4를 사용
//        memoryCache = new LruCache<String, Bitmap>(maxSize) {
//            protected int sizeOf(String key, Bitmap bitmap) {
//                return bitmap.getByteCount();
//            }
//        };

        Disposable disposable = Completable.create(emitter -> {
            synchronized (diskCacheLock) {
                File cacheDir = getDiskCacheDir(context, DISK_CACHE_SUBDIR);
                try {
                    diskLruCache = DiskLruCache.open(cacheDir, 1, 1, DISK_CACHE_SIZE);
                    diskCacheStarting = false;
                    diskCacheLock.notifyAll();
                    emitter.onComplete();
                }
                catch (IOException e) {
                    if (emitter.isDisposed() == false)
                        emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SimpleCompletableObserver() {
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "디스크캐시 생성 실패 : " + e.getMessage(), Toast.LENGTH_LONG).show();
                        super.onError(e);
                    }
                });
    }

    //이미지 캐시에 저장
    public void addBitmapToCache(String key, Bitmap bitmap) {
        synchronized (diskCacheLock) {
            DiskLruCache.Editor editor = null;
            try {
//                memoryCache.put(key, bitmap); //메모리캐시에 저장
                editor = diskLruCache.edit(key);
                if (editor == null)
                    return;

                if (diskLruCache != null && diskLruCache.get(key) == null) {
                    if (writeBitmapToFile(bitmap, editor)) {
                        diskLruCache.flush();
                        editor.commit();
                    }
                }
                else
                    editor.abort();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean writeBitmapToFile(Bitmap bitmap, DiskLruCache.Editor editor) throws IOException, FileNotFoundException {
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(editor.newOutputStream(0), 8 * 1024);
            return bitmap.compress(Bitmap.CompressFormat.PNG, IMAGE_QUALITY, out);
        }
        finally {
            if (out != null)
                out.close();
        }
    }

    public Bitmap getBitmapFromDiskCache(String key) {
//        key = SetBase64.encodeString(key); //url을 base64로 먼저 변환
            synchronized (diskCacheLock) {
                Bitmap bitmap = null;
//                Bitmap bitmap = memoryCache.get(key); //먼저 메모리에 있는지 확인.
                if (bitmap != null)
                    Log.d("ImageCacheHelper", "Memory Hit! Key : " + key);
                else {
                    while (diskCacheStarting) {
                        try {
                            diskCacheLock.wait();
                        }
                        catch (InterruptedException e) {
                        }
                    }

                    if (diskLruCache != null) {
                        DiskLruCache.Snapshot snapshot = null;
                        try {
                            snapshot = diskLruCache.get(key);
                            if (snapshot == null)
                                return null;

                            final InputStream in = snapshot.getInputStream(0);
                            if (in != null) {
                                final BufferedInputStream buffIn = new BufferedInputStream(in, 8 * 1024);
                                bitmap = BitmapFactory.decodeStream(buffIn);
                                Log.d("ImageCacheHelper", "Disk Hit! Key : " + key);
                            }
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        finally {
                            if (snapshot != null)
                                snapshot.close();
                        }
                    }
                }
                return bitmap;
            }
    }

    public void removeBitmapFromCache(String key) {
        synchronized (diskCacheLock) {
//            memoryCache.remove(key);
//            Log.d("ImageCacheHelper", "Memory removed, Key :" + key);
            try {
                diskLruCache.remove(key);
                Log.d("ImageCacheHelper", "Disk removed, Key :" + key);
            } catch (IOException e) {
                Log.d("ImageCacheHelper", "Disk remove Error" + e.getMessage());
            }
        }
    }

    static File getDiskCacheDir(Context context, String uniqueName) {
        final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !isExternalStorageRemovable() ? getExternalCacheDir(context).getPath() : context.getCacheDir()
                .getPath();
        return new File(cachePath + File.separator + uniqueName);
    }

    static boolean isExternalStorageRemovable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD)
            return Environment.isExternalStorageRemovable();

        return true;
    }

    public static File getExternalCacheDir(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO)
            return context.getExternalCacheDir();

        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";

        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }
}



