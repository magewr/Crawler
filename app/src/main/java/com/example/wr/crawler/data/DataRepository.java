package com.example.wr.crawler.data;

import com.example.wr.crawler.data.local.LocalRepository;
import com.example.wr.crawler.data.remote.RemoteRepository;
import com.example.wr.crawler.data.remote.dto.ImageDTO;
import com.example.wr.crawler.ui.listener.SimpleSingleObserver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WR on 2017-11-29.
 */

@Singleton
public class DataRepository {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    private ArrayList<ImageDTO> imageDtoList;

    @Inject
    DataRepository(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        imageDtoList = new ArrayList<>();
    }

    public Completable getImageListItem() {
        Completable completable = Completable.create(emitter -> {
            remoteRepository.getImageList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SimpleSingleObserver<ArrayList<ImageDTO>>() {
                        @Override
                        public void onSuccess(ArrayList<ImageDTO> imageDTOS) {
                            imageDtoList = imageDTOS;
                            emitter.onComplete();
                        }

                        @Override
                        public void onError(Throwable e) {
                            emitter.onError(e);
                        }
                    });
        });
        return completable;
    }

    public Single<File> getImageByName(String imageUrl) {
        String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.lastIndexOf("."));
        Single<File> single = Single.create(emitter -> {
            try {
                File bitmapFile = localRepository.getBitmapFileFromCache(imageName);
                if (bitmapFile != null) {
                    emitter.onSuccess(bitmapFile);
                    return;
                }
                File localFile = remoteRepository.downloadImageFromURL(imageUrl, localRepository.getCacheDirWithFileName(imageName));
                if (localFile != null) {
                    localRepository.addBitmapFileToCache(imageName, localFile);
                    bitmapFile = localRepository.getBitmapFileFromCache(imageName);
                    emitter.onSuccess(bitmapFile);
                } else if (emitter.isDisposed() == false)
                    emitter.onError(new IOException("Bitmap is null"));
            }
            catch (Exception e) {
                if (emitter.isDisposed() == false)
                    emitter.onError(e);
            }
        });
        return single;
    }

    public ArrayList<ImageDTO> getImageDtoList() {
        return imageDtoList;
    }
}
