package com.example.devsmar.simplemvpnews.di.Module;

import android.content.Context;

import com.example.devsmar.simplemvpnews.di.Interface.NewsApplicationScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Context context;

     ActivityModule(Context context) {
        this.context = context;
    }

    @Named("activity_context")
    @NewsApplicationScope
    @Provides
    public Context context() {
        return context;
    }
}
