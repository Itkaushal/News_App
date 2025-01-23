package com.kaushlendraprajapati.newsapp.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.kaushlendraprajapati.newsapp.api.NewsApiService;
import com.kaushlendraprajapati.newsapp.api.RetrofitClient;
import com.kaushlendraprajapati.newsapp.modals.Article;
import com.kaushlendraprajapati.newsapp.modals.NewsResponse;
import com.kaushlendraprajapati.newsapp.persistence.ArticleDao;
import com.kaushlendraprajapati.newsapp.persistence.ArticleDatabase;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private static final String TAG = "NewsRepository";
    private final NewsApiService newsApiService;
    private final ArticleDao articleDao;
    private static final String API_KEY = "56fe361c56ea419cbadf3d198c4dbb8f";

    public NewsRepository(Context context) {
        this.newsApiService = RetrofitClient.getInstance().create(NewsApiService.class);
        ArticleDatabase database = ArticleDatabase.getInstance(context);
        this.articleDao = database.articleDao();
    }

    public MutableLiveData<List<Article>> getArticles(String country, String category) {
        MutableLiveData<List<Article>> liveData = new MutableLiveData<>();

        Call<NewsResponse> call = newsApiService.getTopHeadlines(country, category, API_KEY);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Article> articles = response.body().articles;
                    liveData.postValue(articles);
                } else {
                    Log.e(TAG, "Response is null or unsuccessful. Code: " + response.code());
                    liveData.postValue(Collections.emptyList());
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "Error fetching articles", t);
                liveData.postValue(Collections.emptyList());
            }
        });

        return liveData;
    }
}
