package com.mahmoudashraf.domain.usecases

import com.mahmoudashraf.domain.repositories.NewsRepository

class GetArticlesUseCase(private val newsRepository: NewsRepository) {
    operator fun invoke(source: String) =
        newsRepository.getArticles(source)
}

object ArticlesSource {
    val nextWeb = "the-next-web"
    val associatedPress="associated-press"
}