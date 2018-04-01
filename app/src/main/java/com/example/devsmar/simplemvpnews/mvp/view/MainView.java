package com.example.devsmar.simplemvpnews.mvp.view;

import com.example.devsmar.simplemvpnews.mvp.model.Articles;

import java.util.List;

public interface MainView {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getNewsListSuccess(Articles articles);

}
