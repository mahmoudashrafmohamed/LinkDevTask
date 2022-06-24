package com.mahmoudashraf.linkdevtask.home.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudashraf.core.date.formatDate
import com.mahmoudashraf.core.views.loadImage
import com.mahmoudashraf.core.views.viewBinding
import com.mahmoudashraf.entities.Article
import com.mahmoudashraf.linkdevtask.databinding.ItemNewsBinding


class ArticlesAdapter(
    private val data: MutableList<Article> = mutableListOf(),
    private val onItemClicked: (Article) -> Unit
) :
    RecyclerView.Adapter<ArticlesAdapter.Holder>() {

    class Holder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article,onItemClicked: (Article) -> Unit) {
            with(binding) {
                cvContainer.setOnClickListener{onItemClicked(article)}
                tvTitle.text = article.title
                tvAuthor.text = article.author
                tvDate.text = article.publishedAt.formatDate()
                imgNews.loadImage(article.urlToImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.viewBinding(ItemNewsBinding::inflate))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position], onItemClicked)
    }

    override fun getItemCount() = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Article>) {
        data.addAll(list)
        notifyDataSetChanged()
    }

}