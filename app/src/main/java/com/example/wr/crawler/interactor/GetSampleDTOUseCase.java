package com.example.wr.crawler.interactor;

import com.example.wr.crawler.data.DataRepository;
import com.example.wr.crawler.data.remote.dto.ImageDTO;
import com.example.wr.crawler.interactor.base.ObservableUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by WR on 2017-11-30.
 */

public class GetSampleDTOUseCase extends ObservableUseCase<ImageDTO, Void> {

    private DataRepository dataRepository;

    @Inject
    GetSampleDTOUseCase(DataRepository dataRepository) {
        super(dataRepository);
        this.dataRepository = dataRepository;
    }

    @Override
    protected Observable<ImageDTO> buildUseCaseObservable(Void aVoid) {
        return null;
    }

}
