package com.example.wr.crawler.interactor;

import com.example.wr.crawler.data.DataRepository;
import com.example.wr.crawler.data.remote.dto.SampleDTO;
import com.example.wr.crawler.interactor.base.ObservableUseCase;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by WR on 2017-11-30.
 */

public class GetSampleDTOUseCase extends ObservableUseCase<SampleDTO, Void> {

    private DataRepository dataRepository;

    @Inject
    GetSampleDTOUseCase(DataRepository dataRepository) {
        super(dataRepository);
        this.dataRepository = dataRepository;
    }

    @Override
    protected Observable<SampleDTO> buildUseCaseObservable(Void aVoid) {
        return dataRepository.getSampleDto()
                .repeatWhen(objectObservable -> objectObservable.delay(5, TimeUnit.SECONDS));
    }

}
