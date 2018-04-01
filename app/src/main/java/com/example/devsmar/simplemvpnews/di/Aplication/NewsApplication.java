package com.example.devsmar.simplemvpnews.di.Aplication;

import android.app.Activity;
import android.app.Application;

import com.example.devsmar.simplemvpnews.di.Component.DaggerNewsComponent;
import com.example.devsmar.simplemvpnews.di.Component.NewsComponent;
import com.example.devsmar.simplemvpnews.di.Module.ContextModule;

import timber.log.Timber;

public class NewsApplication extends Application {

    private NewsComponent newsComponent;

    public static NewsApplication get(Activity activity){
        return (NewsApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        newsComponent = DaggerNewsComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public NewsComponent getNewsComponent(){
        return newsComponent;
    }
}