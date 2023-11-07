package com.raffa.movieapp.core.domain.model

class MovieSearchPagingFactory {

    fun create() = MovieSearchPaging(
        page = 1,
        totalPages = 1,
        totalResults = 2,
        movies = listOf(
            MovieSearch(
                id = 1,
                voteAverage = 7.1,
                imageUrl = "Url"
            ),
            MovieSearch(
                id = 2,
                voteAverage = 7.9,
                imageUrl = "Url"
            )
        )
    )
}