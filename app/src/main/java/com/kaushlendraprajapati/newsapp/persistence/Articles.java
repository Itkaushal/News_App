package com.kaushlendraprajapati.newsapp.persistence;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Articles extends com.kaushlendraprajapati.newsapp.modals.Article {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public Date publishedAt;
    public String content;
    public String category;
}

