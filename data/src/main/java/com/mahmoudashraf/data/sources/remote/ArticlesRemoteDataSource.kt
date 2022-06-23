package com.mahmoudashraf.data.sources.remote

import com.mahmoudashraf.core.data.remote.buildApi
import com.mahmoudashraf.entities.ArticlesResponse
import io.reactivex.rxjava3.core.Single

class ArticlesRemoteDataSource(private val articlesAPI: ArticlesAPI = buildApi()) {
    fun getArticles(source : String): Single<ArticlesResponse> = articlesAPI.getArticles(source)
}