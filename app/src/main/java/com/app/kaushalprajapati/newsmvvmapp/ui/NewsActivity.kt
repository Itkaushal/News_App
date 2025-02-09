package com.app.kaushalprajapati.newsmvvmapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.app.kaushalprajapati.newsmvvmapp.R
import com.app.kaushalprajapati.newsmvvmapp.databinding.ActivityNewsBinding
import com.app.kaushalprajapati.newsmvvmapp.db.ArticleDatabase
import com.app.kaushalprajapati.newsmvvmapp.repository.NewsRepository
import com.app.kaushalprajapati.newsmvvmapp.ui.viewmodel.NewsViewModel
import com.app.kaushalprajapati.newsmvvmapp.ui.viewmodel.NewsViewModelProviderFactory

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityNewsBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Initialize View Binding
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Initialize ViewModel
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]

        // ✅ Get the NavHostFragment AFTER setting content view
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment)

        if (navHostFragment is NavHostFragment) {
            navController = navHostFragment.navController
        } else {
            throw IllegalStateException("NavHostFragment not found! Ensure `activity_news.xml` is correct.")
        }

        // ✅ Setup Bottom Navigation with NavController
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}
