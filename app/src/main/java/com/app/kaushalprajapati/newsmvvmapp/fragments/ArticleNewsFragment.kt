package com.app.kaushalprajapati.newsmvvmapp.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.app.kaushalprajapati.newsmvvmapp.R
import com.app.kaushalprajapati.newsmvvmapp.databinding.FragmentArticleBinding
import com.app.kaushalprajapati.newsmvvmapp.ui.NewsActivity
import com.app.kaushalprajapati.newsmvvmapp.ui.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class ArticleNewsFragment : Fragment(R.layout.fragment_article) {

     lateinit var viewModel: NewsViewModel
    private val args: ArticleNewsFragmentArgs by navArgs()
    private lateinit var binding: FragmentArticleBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)

        viewModel = (activity as NewsActivity).viewModel
        val article = args.article

        binding.webView.apply {
            webViewClient = WebViewClient()
            article?.let { loadUrl(it.url) }
        }

        binding.fab.setOnClickListener {
            if (article != null) {
                viewModel.saveArticle(article)
            }
            Snackbar.make(view,"Article saved successfully",Snackbar.LENGTH_SHORT).show()
        }
    }
}