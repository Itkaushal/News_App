package com.app.kaushalprajapati.newsmvvmapp.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.kaushalprajapati.newsmvvmapp.R
import com.app.kaushalprajapati.newsmvvmapp.models.Article
import com.bumptech.glide.Glide

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

   inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       val imgArticle: ImageView = itemView.findViewById(R.id.ivArticleImage)
       val tvTitleArticle: TextView = itemView.findViewById(R.id.tvTitle)
       val tvSourceArticle: TextView = itemView.findViewById(R.id.tvSource)
       val tvDescArticle: TextView = itemView.findViewById(R.id.tvDescription)
       val tvPublishArticle: TextView = itemView.findViewById(R.id.tvPublishedAt)
   }

    private val differCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
           return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]

        holder.apply {
            Glide.with(itemView.context).load(article.urlToImage).into(imgArticle)
            tvTitleArticle.text = article.title
            tvDescArticle.text = article.description
            tvPublishArticle.text = article.publishedAt
            tvSourceArticle.text = article.source?.name ?: "Unknown Source"

            setOnItemClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size

    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener : (Article) -> Unit){
        onItemClickListener = listener
    }
}