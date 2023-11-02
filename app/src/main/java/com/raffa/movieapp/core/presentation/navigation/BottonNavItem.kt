package com.raffa.movieapp.core.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.raffa.movieapp.core.util.Constants.MOVIE_DETAIL_ARGUMENT_KEY

sealed class BottonNavItem(val title: String, val icon: ImageVector, val route: String){
    object MoviePopular: BottonNavItem(
        title = "Filmes Populares",
        icon = Icons.Default.Movie,
        route = "movie_popular_screen"
    )

    object  MovieSearch: BottonNavItem(
        title = "Pesquisar",
        icon = Icons.Default.Search,
        route = "movie_search_screen"
    )

    object  MovieFavorite: BottonNavItem(
        title = "Favoritos",
        icon = Icons.Default.Favorite,
        route = "movie_favorite_screen"
    )

    object MovieDetail: BottonNavItem(
        title = "Detalhes",
        icon = Icons.Default.Details,
        route = "movie_detail_destination?$MOVIE_DETAIL_ARGUMENT_KEY=" +
                "{$MOVIE_DETAIL_ARGUMENT_KEY}}"
    ){
        fun passMovieId(movieId: Int) =
            "movie_detail_destination?$MOVIE_DETAIL_ARGUMENT_KEY=$movieId"
    }
}
