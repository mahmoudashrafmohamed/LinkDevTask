package com.mahmoudashraf.linkdevtask.details.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mahmoudashraf.core.date.formatDate
import com.mahmoudashraf.core.views.loadImage
import com.mahmoudashraf.core.views.viewBinding
import com.mahmoudashraf.entities.Article
import com.mahmoudashraf.linkdevtask.R
import com.mahmoudashraf.linkdevtask.databinding.FragmentDetailsBinding
import com.mahmoudashraf.linkdevtask.home.fragment.ARTICLE_KEY

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val binding by viewBinding(FragmentDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article : Article? = arguments?.getParcelable(ARTICLE_KEY)
        article?.let {
            with(binding){
                tvTitle.text = article.title
                tvAuthor.text = article.author
                tvDesc.text = article.description
                tvDate.text = article.publishedAt.formatDate()
                imgNews.loadImage(article.urlToImage)
            }
        }

    }

}