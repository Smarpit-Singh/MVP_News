package com.example.devsmar.simplemvpnews.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.devsmar.simplemvpnews.R;
import com.example.devsmar.simplemvpnews.adapter.NewsListAdapter;
import com.example.devsmar.simplemvpnews.di.Aplication.NewsApplication;
import com.example.devsmar.simplemvpnews.di.Component.DaggerNewsComponent;
import com.example.devsmar.simplemvpnews.di.Component.NewsComponent;
import com.example.devsmar.simplemvpnews.di.MainActivityFeatures.DaggerMainActivityComponent;
import com.example.devsmar.simplemvpnews.di.MainActivityFeatures.MainActivityComponent;
import com.example.devsmar.simplemvpnews.di.MainActivityFeatures.MainActivityModule;
import com.example.devsmar.simplemvpnews.di.Module.ContextModule;
import com.example.devsmar.simplemvpnews.mvp.model.Articles;
import com.example.devsmar.simplemvpnews.mvp.model.RxjavaService;
import com.example.devsmar.simplemvpnews.mvp.presenter.NewsPresenter;
import com.example.devsmar.simplemvpnews.mvp.view.MainView;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainView {

    RecyclerView recyclerView;
    Picasso picasso;
    ProgressBar progressBar;
    Context context;
    EditText editText;
    TextView empty_text;
    Button button;

    //----------------------------Dagger 2
    @Inject
    RxjavaService service;
    @Inject
    NewsListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        renderView();
        init();

        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .newsComponent(NewsApplication.get(this).getNewsComponent())
                .build();

        mainActivityComponent.injectMainActivity(this);


    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empty_text.setVisibility(View.GONE);

                NewsPresenter presenter = new NewsPresenter(service, MainActivity.this);
                presenter.getNewsList(editText.getText().toString());
            }
        });
    }

    private void renderView() {
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        editText = findViewById(R.id.edit_text);
        button = findViewById(R.id.button);
        empty_text = findViewById(R.id.empty_text);
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
        empty_text.setVisibility(View.VISIBLE);
        empty_text.setText(appErrorMessage);
    }

    @Override
    public void getNewsListSuccess(Articles articles) {
        mAdapter.setItems(articles.getArticles());
        recyclerView.setAdapter(mAdapter);
        Log.i("fuck","result success");
    }
}
