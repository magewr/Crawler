package com.example.wr.crawler.data.remote;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;

import com.example.wr.crawler.data.remote.dto.SampleDTO;
import com.example.wr.crawler.data.remote.service.BaseUrl;
import com.example.wr.crawler.data.remote.service.SampleApiService;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Call;

import static com.example.wr.crawler.data.remote.ServiceError.ERROR_UNDEFINED;
import static com.example.wr.crawler.data.remote.ServiceError.NETWORK_ERROR;
import static com.example.wr.crawler.data.remote.ServiceError.SUCCESS_CODE;

/**
 * Created by WR on 2017-11-29.
 */

public class RemoteRepository {

    private ServiceGenerator serviceGenerator;

    @Inject
    RemoteRepository(ServiceGenerator serviceGenerator) {
        this. serviceGenerator = serviceGenerator;
    }

    public Observable<SampleDTO> getSampleDto() {
        Observable<SampleDTO> sampleDTOObservable = Observable.create( emitter -> {
            try {
                SampleApiService sampleApiService = serviceGenerator.createService(SampleApiService.class, BaseUrl.BASE_URL);
                ServiceResponse serviceResponse = processCall(sampleApiService.getSampleDetailData(), false);
                if (serviceResponse.getCode() == SUCCESS_CODE) {
                    SampleDTO sampleModel = (SampleDTO) serviceResponse.getData();
                    emitter.onNext(sampleModel);
                    emitter.onComplete();
                } else {
                    Throwable throwable = new NetworkErrorException();
                    emitter.onError(throwable);
                }
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
        return sampleDTOObservable;
    }

    @NonNull
    private ServiceResponse processCall(Call call, boolean isVoid) {
        try {
            retrofit2.Response response = call.execute();
            if (response == null) {
                return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
            }
            int responseCode = response.code();
            if (response.isSuccessful()) {
                return new ServiceResponse(responseCode, isVoid ? null : response.body());
            } else {
                ServiceError ServiceError;
                ServiceError = new ServiceError(response.message(), responseCode);
                return new ServiceResponse(ServiceError);
            }
        } catch (IOException e) {
            return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
        }
    }
}
