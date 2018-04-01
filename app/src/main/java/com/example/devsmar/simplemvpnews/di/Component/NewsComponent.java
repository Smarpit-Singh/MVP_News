package com.example.devsmar.simplemvpnews.di.Component;


import com.example.devsmar.simplemvpnews.di.Interface.NewsApplicationScope;
import com.example.devsmar.simplemvpnews.di.Module.PicassoModule;
import com.example.devsmar.simplemvpnews.di.Module.RetrofitModule;
import com.example.devsmar.simplemvpnews.mvp.model.NewsRestService;
import com.example.devsmar.simplemvpnews.mvp.model.RxjavaService;
import com.squareup.picasso.Picasso;

import dagger.Component;

@NewsApplicationScope
@Component(modules = {RetrofitModule.class, PicassoModule.class})
public interface NewsComponent {

    RxjavaService getNewsService();

    Picasso getPicasso();
}
