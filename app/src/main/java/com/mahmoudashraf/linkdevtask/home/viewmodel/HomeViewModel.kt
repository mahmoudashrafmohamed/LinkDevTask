package com.mahmoudashraf.linkdevtask.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mahmoudashraf.core.base.BaseViewModel
import com.mahmoudashraf.data.repositories.NewsRepositoryImpl
import com.mahmoudashraf.domain.usecases.ArticlesSource
import com.mahmoudashraf.domain.usecases.GetArticlesUseCase
import com.mahmoudashraf.entities.Article
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(
    private val getArticlesUseCase: GetArticlesUseCase = GetArticlesUseCase(
        NewsRepositoryImpl()
    )
) : BaseViewModel() {
    internal val screenState by lazy { MutableLiveData<GetArticlesState>() }

    private fun loadArticles() {
        screenState.postValue(GetArticlesState.Loading)
        getArticlesUseCase(source = ArticlesSource.nextWeb)
            .zipWith(
                getArticlesUseCase(source = ArticlesSource.associatedPress)
            ) { nextWebResponse, associatedPressResponse ->
                return@zipWith mutableListOf<Article>().also {
                    it.addAll(nextWebResponse.articles)
                    it.addAll(associatedPressResponse.articles)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                screenState.postValue(GetArticlesState.Success(it))
            }, { it.printStackTrace()
                screenState.postValue(GetArticlesState.Error(it))
            })
            .also { addDisposable(it) }
    }
}

sealed class GetArticlesState {
    object Loading : GetArticlesState()
    data class Success(val articles: List<Article>) : GetArticlesState()
    data class Error(val throwable: Throwable) : GetArticlesState()
}