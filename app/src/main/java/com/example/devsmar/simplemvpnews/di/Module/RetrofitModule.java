package com.example.devsmar.simplemvpnews.di.Module;

import com.example.devsmar.simplemvpnews.di.Interface.NewsApplicationScope;
import com.example.devsmar.simplemvpnews.mvp.model.NewsRestService;
import com.example.devsmar.simplemvpnews.mvp.model.RxjavaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OkHttpClientModule.class)
public class RetrofitModule {

    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    @NewsApplicationScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory, RxJavaCallAdapterFactory factory){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(factory)
                .build();
    }

    @Provides
    public RxJavaCallAdapterFactory factory(){
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    public NewsRestService newsRestService(Retrofit retrofit){
        return retrofit.create(NewsRestService.class);
    }

    @Provides
    public RxjavaService rxjavaService(NewsRestService newsRestService){
        return new RxjavaService(newsRestService);
    }
}
