package com.example.wr.crawler.ui.content.splash;

import com.example.wr.crawler.data.DataRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by loadm on 2018-03-04.
 */
@RunWith(MockitoJUnitRunner.class)
public class SplashPresenterTest {

    private SplashPresenter presenter;
    @Mock   DataRepository dataRepository;
    @Mock   SplashContract.View view;

    @Before
    public void setUp (){
        MockitoAnnotations.initMocks(this);
        presenter = new SplashPresenter(dataRepository);
        presenter.setView(view);
    }

    @Test
    public void networkErrorTest() {
        //네트워크 에러 등으로 Exception 발생 시 다이얼로그 호출 테스트
        when(dataRepository.getImageListItem()).thenReturn(new Completable() {
            @Override
            protected void subscribeActual(CompletableObserver s) {
                s.onError(new IOException());
            }
        });
        presenter.getImageList();
        verify(view).showErrorDialog();
    }

}