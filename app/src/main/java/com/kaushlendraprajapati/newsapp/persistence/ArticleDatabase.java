package com.kaushlendraprajapati.newsapp.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Articles.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class ArticleDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();

    private static ArticleDatabase instance;

    public static ArticleDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (ArticleDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ArticleDatabase.class,
                            "article_database"
                    ).fallbackToDestructiveMigration().build();
                }
            }
        }
        return instance;
    }
}

