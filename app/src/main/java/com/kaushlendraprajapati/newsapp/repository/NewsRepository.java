package com.kaushlendraprajapati.newsapp.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.kaushlendraprajapati.newsapp.api.NewsApiService;
import com.kaushlendraprajapati.newsapp.api.RetrofitClient;
import com.kaushlendraprajapati.newsapp.modals.Article;
import com.kaushlendraprajapati.newsapp.modals.NewsResponse;
import com.kaushlendraprajapati.newsapp.persistence.ArticleDao;
import com.kaushlendraprajapati.newsapp.persistence.ArticleDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private final NewsApiService newsApiService;
    private final ArticleDao articleDao;

    public NewsRepository(Context context) {
        this.newsApiService = RetrofitClient.getInstance().create(NewsApiService.class);
        ArticleDatabase database = ArticleDatabase.getInstance(context);
        this.articleDao = database.articleDao();
    }

    public MutableLiveData<List<Article>> getArticles(String country, String category) {
        MutableLiveData<List<Article>> liveData = new MutableLiveData<>();

        Call<NewsResponse> call = newsApiService.getTopHeadlines(country, category, "ad24633fa0d4499e94b434501c30fce2");
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Article> articles = response.body().articles;
                    liveData.postValue(articles); // Ensure correct type
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("NewsRepository", "Error fetching articles", t);
            }
        });

        return liveData;
    }
}
