package com.kaushlendraprajapati.newsapp.persistence;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<Articles> articles);

    @Query("SELECT * FROM Articles WHERE category = :category")
    List<com.kaushlendraprajapati.newsapp.modals.Article> getArticlesByCategory(String category);
}

