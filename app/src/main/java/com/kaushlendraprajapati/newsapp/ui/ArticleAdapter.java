package com.kaushlendraprajapati.newsapp.ui;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kaushlendraprajapati.newsapp.R;
import com.kaushlendraprajapati.newsapp.modals.Article;

import java.util.List;


public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    private List<Article> articleList;

    public void submitList(List<Article> articles) {
        this.articleList = articles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article,parent,false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        com.kaushlendraprajapati.newsapp.modals.Article article = articleList.get(position);
        holder.bind(article);
    }

    @Override
    public int getItemCount() {
        return (articleList != null) ? articleList.size() : 0;
    }
}

