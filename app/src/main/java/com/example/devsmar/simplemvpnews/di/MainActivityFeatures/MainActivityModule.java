package com.example.devsmar.simplemvpnews.di.MainActivityFeatures;

import com.example.devsmar.simplemvpnews.adapter.NewsListAdapter;
import com.example.devsmar.simplemvpnews.ui.MainActivity;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;


@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public NewsListAdapter newsListAdapter(Picasso picasso){
        return new NewsListAdapter(mainActivity, picasso);
    }
}
