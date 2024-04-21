package com.kaushlendraprajapati.newsapp;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.kaushlendraprajapati.newsapp.modals.Article;
import com.kaushlendraprajapati.newsapp.ui.ArticleAdapter;
import com.kaushlendraprajapati.newsapp.viewmodal.NewsViewModal;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_WRITE_EXTERNAL_STORAGE = 100;
    private NewsViewModal newsViewModel; // Corrected ViewModel name and import
    private ArticleAdapter articleAdapter;
    private RecyclerView recyclerView;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsViewModel = new ViewModelProvider(this).get(NewsViewModal.class); // Corrected ViewModel name

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        articleAdapter = new ArticleAdapter();
        recyclerView.setAdapter(articleAdapter);

        tabLayout = findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("General"));
        tabLayout.addTab(tabLayout.newTab().setText("Business"));
        tabLayout.addTab(tabLayout.newTab().setText("Technology"));
        tabLayout.addTab(tabLayout.newTab().setText("Sports"));
        tabLayout.addTab(tabLayout.newTab().setText("Entertainment"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fetchArticles(tab.getText().toString().toLowerCase());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        // Fetch general news by default
        fetchArticles("general");
    }

    private void fetchArticles(String category) {
        newsViewModel.getArticles(category).observe(this, new Observer<List<Article>>() { // Correct Observer type
            @Override
            public void onChanged(List<Article> articles) {
                articleAdapter.submitList(articles);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied. Cannot save image.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    }
