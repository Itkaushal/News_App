package com.app.kaushalprajapati.newsmvvmapp.repository

import androidx.room.Database
import androidx.room.Query
import com.app.kaushalprajapati.newsmvvmapp.api.RetrofitInstance
import com.app.kaushalprajapati.newsmvvmapp.db.ArticleDatabase
import com.app.kaushalprajapati.newsmvvmapp.models.Article
import retrofit2.Retrofit
import java.util.Locale.IsoCountryCode

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery: String , pageNumber: Int) =
        RetrofitInstance.api.getSearchForNews(searchQuery,pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}