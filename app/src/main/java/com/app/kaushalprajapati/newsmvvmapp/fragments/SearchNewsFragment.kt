package com.app.kaushalprajapati.newsmvvmapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.kaushalprajapati.newsmvvmapp.R
import com.app.kaushalprajapati.newsmvvmapp.adapter.NewsAdapter
import com.app.kaushalprajapati.newsmvvmapp.databinding.FragmentSearchNewsBinding
import com.app.kaushalprajapati.newsmvvmapp.ui.NewsActivity
import com.app.kaushalprajapati.newsmvvmapp.ui.viewmodel.NewsViewModel
import com.app.kaushalprajapati.newsmvvmapp.util.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import com.app.kaushalprajapati.newsmvvmapp.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    private lateinit var viewModel : NewsViewModel
    private val TAG ="SearchNewsFragment"
    private lateinit var binding: FragmentSearchNewsBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchNewsBinding.bind(view)

        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle =Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(
                R.id.action_searchNewsFragment_to_articleNewsFragment,
                bundle
            )
        }



        var job: Job? = null
        binding.etSearch.addTextChangedListener { editable->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if(editable.toString().isNotEmpty()){
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
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
        binding.rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}