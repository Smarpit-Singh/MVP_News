package com.example.devsmar.simplemvpnews.di.MainActivityFeatures;

import com.example.devsmar.simplemvpnews.adapter.NewsListAdapter;
import com.example.devsmar.simplemvpnews.adapter.NewsListAdapter.OnClickListener;
import com.example.devsmar.simplemvpnews.mvp.model.RxjavaService;
import com.example.devsmar.simplemvpnews.mvp.presenter.NewsPresenter;
import com.example.devsmar.simplemvpnews.mvp.view.MainView;
import com.example.devsmar.simplemvpnews.ui.MainActivity;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;


@Module
public class MainActivityModule {

    private final MainActivity mainActivity;
    private final MainView mainView;
    private final OnClickListener onClickListener;

    public MainActivityModule(MainActivity mainActivity, MainView mainView, OnClickListener onClickListener) {
        this.mainActivity = mainActivity;
        this.mainView = mainView;
        this.onClickListener = onClickListener;
    }

    @Provides
    @MainActivityScope
    public NewsListAdapter newsListAdapter(Picasso picasso){
        return new NewsListAdapter(mainActivity, picasso, onClickListener);
    }

    @Provides
    @MainActivityScope
    public NewsPresenter newsPresenter(RxjavaService service) {
        return new NewsPresenter(service, mainView);
    }
}
