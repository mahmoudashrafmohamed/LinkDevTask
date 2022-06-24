package com.mahmoudashraf.linkdevtask.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import com.mahmoudashraf.core.base.Function
import com.mahmoudashraf.core.base.getViewModel
import com.mahmoudashraf.core.views.viewBinding
import com.mahmoudashraf.entities.Article
import com.mahmoudashraf.linkdevtask.R
import com.mahmoudashraf.linkdevtask.databinding.FragmentHomeBinding
import com.mahmoudashraf.linkdevtask.home.adapter.ArticlesAdapter
import com.mahmoudashraf.linkdevtask.home.viewmodel.GetArticlesState
import com.mahmoudashraf.linkdevtask.home.viewmodel.HomeViewModel

class HomeFragment(
    private val viewModelProvider: Function<ViewModelStoreOwner, HomeViewModel> = {
        getViewModel(it) { HomeViewModel() }
    }
) : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by lazy { viewModelProvider(this) }
    private val adapter by lazy { ArticlesAdapter(onItemClicked = ::onItemClicked) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.screenState.observe(viewLifecycleOwner, ::onScreenStateChange)
    }

    private fun onScreenStateChange(state: GetArticlesState) {
        when (state) {
            GetArticlesState.Loading -> showLoading()
            is GetArticlesState.Success -> handleSuccessState(state.articles)
            is GetArticlesState.Error -> handleError()
        }
    }

    private fun handleError() {
        Toast.makeText(
            context,
            getString(R.string.something_went_wrong_error_msg),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoading() {
        binding.progressBar.isVisible = true
    }

    private fun hideLoading() {
        binding.progressBar.isVisible = false
    }

    private fun handleSuccessState(articles: List<Article>) {
        hideLoading()
        adapter.submitList(articles)
    }

    private fun initView() {
        binding.rvNews.adapter = adapter
    }

    private fun onItemClicked(article: Article) {
        findNavController().navigate(
            R.id.detailsFragment,
            Bundle().apply { putParcelable(ARTICLE_KEY, article) })
    }

}
const val ARTICLE_KEY = "ARTICLE_KEY"