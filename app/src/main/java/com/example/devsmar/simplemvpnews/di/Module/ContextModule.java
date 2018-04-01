package com.example.devsmar.simplemvpnews.di.Module;

import android.content.Context;

import com.example.devsmar.simplemvpnews.di.Interface.AplicationContex;
import com.example.devsmar.simplemvpnews.di.Interface.NewsApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @AplicationContex
    @NewsApplicationScope
    @Provides
    public Context context() {
        return context.getApplicationContext();
    }
}
