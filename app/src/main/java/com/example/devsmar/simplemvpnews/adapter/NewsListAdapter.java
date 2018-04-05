package com.example.devsmar.simplemvpnews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.devsmar.simplemvpnews.R;
import com.example.devsmar.simplemvpnews.mvp.model.Article;
import com.example.devsmar.simplemvpnews.ui.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private List<Article> articlesList = new ArrayList<>();
    private MainActivity mainActivity;
    private Picasso picasso;
    private OnClickListener onClickListener;

    public NewsListAdapter(MainActivity mainActivity, Picasso picasso, OnClickListener onClickListener) {
        this.mainActivity = mainActivity;
        this.picasso = picasso;
        this.onClickListener = onClickListener;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.txt_title.setText(articlesList.get(position).getTitle());

        if (articlesList.get(position).getDescription() == null || articlesList.get(position).getDescription().isEmpty()) {
            holder.txt_description.setText("No description");
        }else {
            holder.txt_description.setText(articlesList.get(position).getDescription());
        }

        if (articlesList.get(position).getUrlToImage() == null || articlesList.get(position).getUrlToImage().isEmpty()) {
            holder.img_news.setImageDrawable(null);
            holder.img_news.setVisibility(View.GONE);
        }
        else {
            holder.img_news.setVisibility(View.VISIBLE);
            Picasso.with(mainActivity)
                    .load(articlesList.get(position).getUrlToImage())
                    .resize(200,200)
                    .centerCrop()
                    .into(holder.img_news);

        }
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_layout, parent, false);
        return new NewsViewHolder(view);
    }

    public interface OnClickListener {
        void onClick(int position, String url);
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public void setItems(List<Article> articles) {
        articlesList = articles;
        notifyDataSetChanged();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img_news;
        TextView txt_title, txt_description;

        public NewsViewHolder(View itemView) {
            super(itemView);

            img_news = itemView.findViewById(R.id.img_news);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_description = itemView.findViewById(R.id.txt_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(getAdapterPosition(), articlesList.get(getAdapterPosition()).getUrl());
        }
    }
}
