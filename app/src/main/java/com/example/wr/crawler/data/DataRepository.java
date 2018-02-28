package com.example.wr.crawler.data;

import com.example.wr.crawler.data.local.LocalRepository;
import com.example.wr.crawler.data.remote.RemoteRepository;
import com.example.wr.crawler.data.remote.dto.ImageDTO;
import com.example.wr.crawler.ui.listener.SimpleSingleObserver;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;
import okhttp3.internal.cache.DiskLruCache;

/**
 * Created by WR on 2017-11-29.
 */

@Singleton
public class DataRepository {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    @Getter
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
}
