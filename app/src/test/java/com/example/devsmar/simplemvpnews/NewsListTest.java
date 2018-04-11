package com.example.devsmar.simplemvpnews;


import com.example.devsmar.simplemvpnews.mvp.model.Articles;
import com.example.devsmar.simplemvpnews.mvp.model.RxjavaService;
import com.example.devsmar.simplemvpnews.mvp.presenter.NewsPresenter;
import com.example.devsmar.simplemvpnews.mvp.view.MainView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import rx.Subscription;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class NewsListTest {

    private NewsPresenter newsPresenter;

    @Mock
    private RxjavaService rxjavaService;
    @Mock
    private MainView mainView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        newsPresenter = new NewsPresenter(rxjavaService, mainView);
    }

    @After
    public void tearDown() {
        mainView = null;
        newsPresenter.onStop();
    }

    @Test
    public void Testing_The_Result() {
        final RxjavaService.GetNewsCallback[] callback = new RxjavaService.GetNewsCallback[1];
        Mockito.when(rxjavaService.getNews(ArgumentMatchers.anyString(), ArgumentMatchers.any(RxjavaService.GetNewsCallback.class))).thenAnswer(new Answer<Subscription>() {
            public Subscription answer(InvocationOnMock invocationOnMock) {
                callback[0] = invocationOnMock.getArgument(1);
                return mock(Subscription.class);
            }
        });
        newsPresenter.getNewsList("some url");
        Articles articles = new Articles();

        callback[0].onSuccess(articles);

        verify(mainView).removeWait();
        verify(mainView).getNewsListSuccess(articles);

    }

}
