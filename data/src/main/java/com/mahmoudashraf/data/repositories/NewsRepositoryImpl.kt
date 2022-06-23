package com.mahmoudashraf.data.repositories

import com.mahmoudashraf.data.sources.remote.ArticlesRemoteDataSource
import com.mahmoudashraf.domain.repositories.NewsRepository
import com.mahmoudashraf.entities.ArticlesResponse
import io.reactivex.rxjava3.core.Single

class NewsRepositoryImpl(private val dataSource: ArticlesRemoteDataSource = ArticlesRemoteDataSource()) : NewsRepository {
    override fun getArticles(source: String): Single<ArticlesResponse> {
        return dataSource.getArticles(source)
    }
}