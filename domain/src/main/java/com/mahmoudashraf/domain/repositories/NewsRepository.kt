package com.mahmoudashraf.domain.repositories

import com.mahmoudashraf.entities.ArticlesResponse
import io.reactivex.rxjava3.core.Single

interface NewsRepository {
    fun getArticles(source : String): Single<ArticlesResponse>
}