package com.example.devsmar.simplemvpnews.ui;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.devsmar.simplemvpnews.R;
import com.example.devsmar.simplemvpnews.adapter.NewsListAdapter;
import com.example.devsmar.simplemvpnews.di.Aplication.NewsApplication;
import com.example.devsmar.simplemvpnews.di.MainActivityFeatures.DaggerMainActivityComponent;
import com.example.devsmar.simplemvpnews.di.MainActivityFeatures.MainActivityModule;
import com.example.devsmar.simplemvpnews.mvp.model.Articles;
import com.example.devsmar.simplemvpnews.mvp.model.RxjavaService;
import com.example.devsmar.simplemvpnews.mvp.presenter.NewsPresenter;
import com.example.devsmar.simplemvpnews.mvp.view.MainView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView, NewsListAdapter.OnClickListener {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    EditText search_text;
    View mikki_view;
    Button button;

    //----------------------------Dagger 2
    @Inject
    RxjavaService service;
    @Inject
    NewsListAdapter mAdapter;
    @Inject
    NewsPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        renderView();
        init();

        DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this, this, this))
                .newsComponent(NewsApplication.get(this).getNewsComponent())
                .build().injectMainActivity(this);

    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar.setVisibility(View.GONE);

        hideKeyboard();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty()) {
                    mikki_view.setVisibility(View.GONE);
                    presenter.getNewsList(search_text.getText().toString());

                } else {
                    Snackbar.make(recyclerView, "Type something buddy !!!.", Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }

    private void hideKeyboard() {
        View view = MainActivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean isEmpty() {
        return search_text.getText().toString() == null || search_text.getText().toString().isEmpty();
    }

    private void renderView() {
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        search_text = findViewById(R.id.edit_text);
        button = findViewById(R.id.button);
        mikki_view = findViewById(R.id.mikki_view);
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        Snackbar.make(recyclerView, appErrorMessage, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void getNewsListSuccess(Articles articles) {
        if (articles.getArticles().size() > 0) {
            mAdapter.setItems(articles.getArticles());
            recyclerView.setAdapter(mAdapter);
        } else {
            Snackbar.make(recyclerView, "Sorry, there is no Data.", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(int position, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url.toString()));
        startActivity(intent);
    }
}
