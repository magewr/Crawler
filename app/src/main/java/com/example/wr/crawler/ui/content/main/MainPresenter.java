package com.example.wr.crawler.ui.content.main;

import com.example.wr.crawler.data.remote.dto.SampleDTO;
import com.example.wr.crawler.interactor.GetSampleDTOUseCase;
import com.example.wr.crawler.ui.base.Presenter;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by WR on 2017-11-27.
 */

public class MainPresenter extends Presenter<MainContract.View> implements MainContract.Presenter {

    GetSampleDTOUseCase useCase;

    @Inject
    public MainPresenter(GetSampleDTOUseCase useCase){
        this.useCase = useCase;
    }

    @Override
    public void getSampleData() {
        useCase.execute(new SampleDataObserver(), null);
    }

    @Override
    public void onCreatePresenter() {
        super.onCreatePresenter();
        getSampleData();
    }

    @Override
    public void dispose() {
        useCase.dispose();
    }

    private final class SampleDataObserver extends DisposableObserver<SampleDTO> {
        @Override
        public void onNext(SampleDTO sampleDTO) {
            getView().showSampleData(sampleDTO);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

}
