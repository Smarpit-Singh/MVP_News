package com.example.devsmar.simplemvpnews.mvp.model;


import android.util.Log;

import com.example.devsmar.simplemvpnews.utils.NetworkError;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxjavaService {

    private final NewsRestService newsRestService;

    public RxjavaService(NewsRestService newsRestService) {
        this.newsRestService = newsRestService;
    }


    public interface GetNewsCallback {
        void onSuccess(Articles articles);

        void onError(NetworkError networkError);
    }


    public Subscription getNews(String q, final GetNewsCallback getNewsCallback) {
        Log.i("stuck", "service called");
        return newsRestService.getNewsBySearch(q,"8dca7dea475e41e49518b2c61131e118",100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Articles>>() {
                    @Override
                    public Observable<? extends Articles> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<Articles>() {
                    @Override
                    public void onCompleted() {
                        Log.i("stuck", "complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        getNewsCallback.onError(new NetworkError(e));
                        Log.i("stuck", e.getMessage());
                    }

                    @Override
                    public void onNext(Articles articles) {
                        getNewsCallback.onSuccess(articles);
                        Log.i("stuck", "Onnext");
                    }
                });

    }
}
