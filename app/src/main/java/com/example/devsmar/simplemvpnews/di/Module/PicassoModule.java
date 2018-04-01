package com.example.devsmar.simplemvpnews.di.Module;


import android.content.Context;

import com.example.devsmar.simplemvpnews.di.Interface.AplicationContex;
import com.example.devsmar.simplemvpnews.di.Interface.NewsApplicationScope;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = OkHttpClientModule.class)
public class PicassoModule {


    @NewsApplicationScope
    @Provides
    public Picasso picasso(@AplicationContex Context context, OkHttp3Downloader okHttp3Downloader){
        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }

    @Provides
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient){
        return new OkHttp3Downloader(okHttpClient);
    }
}
