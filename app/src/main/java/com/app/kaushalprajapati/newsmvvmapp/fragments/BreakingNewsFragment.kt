package com.app.kaushalprajapati.newsmvvmapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.kaushalprajapati.newsmvvmapp.R
import com.app.kaushalprajapati.newsmvvmapp.adapter.NewsAdapter
import com.app.kaushalprajapati.newsmvvmapp.databinding.FragmentBreakingNewsBinding
import com.app.kaushalprajapati.newsmvvmapp.ui.NewsActivity
import com.app.kaushalprajapati.newsmvvmapp.ui.viewmodel.NewsViewModel
import com.app.kaushalprajapati.newsmvvmapp.util.Resource

class BreakingNewsFragment: Fragment(R.layout.fragment_breaking_news) {

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter : NewsAdapter
    private lateinit var binding: FragmentBreakingNewsBinding
    private val TAG ="BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBreakingNewsBinding.bind(view)

        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle =Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleNewsFragment,
                bundle
            )
        }

        viewModel.breakingNews.observe(viewLifecycleOwner , Observer { response ->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let {
                        newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let { messege->
                        Log.e(TAG, "an error occured:  $messege")
                    }
                }

                is Resource.Loading ->{
                    showProgressBar()

                }
            }

        })
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }


    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}