package com.mahmoudashraf.data.sources.remote

import com.mahmoudashraf.entities.ArticlesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesAPI {
    @GET("/v1/articles")
    fun getArticles(
        @Query("source") source: String,
        @Query("apiKey") apiKey: String = "533af958594143758318137469b41ba9" //todo set this in gradle
    ): Single<ArticlesResponse>
}