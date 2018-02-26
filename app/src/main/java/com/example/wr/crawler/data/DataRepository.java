package com.example.wr.crawler.data;

import com.example.wr.crawler.data.local.LocalRepository;
import com.example.wr.crawler.data.remote.RemoteRepository;
import com.example.wr.crawler.data.remote.dto.SampleDTO;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by WR on 2017-11-29.
 */

public class DataRepository {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    @Inject
    DataRepository(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public Observable<SampleDTO> getSampleDto() {
        return remoteRepository.getSampleDto();
    }
}
