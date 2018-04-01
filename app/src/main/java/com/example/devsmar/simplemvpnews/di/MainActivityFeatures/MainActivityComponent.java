package com.example.devsmar.simplemvpnews.di.MainActivityFeatures;


import com.example.devsmar.simplemvpnews.di.Component.NewsComponent;
import com.example.devsmar.simplemvpnews.ui.MainActivity;

import dagger.Component;

@Component(modules = MainActivityModule.class, dependencies = NewsComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);

}
