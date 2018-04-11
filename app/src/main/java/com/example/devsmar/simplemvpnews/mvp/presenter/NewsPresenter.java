package com.example.devsmar.simplemvpnews.mvp.presenter;


import android.util.Log;

import com.example.devsmar.simplemvpnews.mvp.model.Articles;
import com.example.devsmar.simplemvpnews.mvp.model.RxjavaService;
import com.example.devsmar.simplemvpnews.mvp.view.MainView;
import com.example.devsmar.simplemvpnews.utils.NetworkError;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class NewsPresenter {

    private final RxjavaService service;
    private final MainView view;
    private CompositeSubscription subscriptions;

    public NewsPresenter(RxjavaService service, MainView view) {
        this.service = service;
        this.view = view;
        subscriptions = new CompositeSubscription();
    }

    public void getNewsList(String urlQ){
        view.showWait();

        Subscription subscription = service.getNews(urlQ ,new RxjavaService.GetNewsCallback() {
            @Override
            public void onSuccess(Articles articles) {
                view.removeWait();
                view.getNewsListSuccess(articles);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
                Log.i("huh", networkError.getMessage());
            }
        });


        subscriptions.add(subscription);
    }

    public void onStop(){
        subscriptions.unsubscribe();
    }
}
