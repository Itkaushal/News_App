package com.kaushlendraprajapati.newsapp.viewmodal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kaushlendraprajapati.newsapp.modals.Article;
import com.kaushlendraprajapati.newsapp.repository.NewsRepository;

import java.util.List;

public class NewsViewModal extends AndroidViewModel {
    private final NewsRepository newsRepository;

    public NewsViewModal(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
    }

    public MutableLiveData<List<Article>> getArticles(String category) {
        return newsRepository.getArticles("in", category);
    }

    public LiveData<List<Article>> searchArticles(String query) {
        return newsRepository.getArticles("in",query);
    }
}
