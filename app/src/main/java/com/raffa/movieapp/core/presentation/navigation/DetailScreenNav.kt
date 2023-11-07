package com.raffa.movieapp.core.presentation.navigation

import com.raffa.movieapp.core.util.Constants

sealed class DetailScreenNav(val route: String) {
    object DetailScreen: DetailScreenNav(
        route = "movie_detail_destination?${Constants.MOVIE_DETAIL_ARGUMENT_KEY}=" +
                "{${Constants.MOVIE_DETAIL_ARGUMENT_KEY}}"
    ){
        fun passMovieId(movieId: Int) =
            "movie_detail_destination?${Constants.MOVIE_DETAIL_ARGUMENT_KEY}=$movieId"
    }
}