package com.raffa.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raffa.movieapp.R
import com.raffa.movieapp.core.domain.model.MovieDetails


@Composable
fun MovieInfoContent(
    movieDetails: MovieDetails, modifier: Modifier = Modifier
){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ){
        MovieInfo(
            name = stringResource(id = R.string.vote_average),
            value = movieDetails?.voteAverage.toString()
        )
        MovieInfo(
            name = stringResource(id = R.string.duration),
            value = stringResource(id = R.string.duration_minutes,
                movieDetails?.duration.toString())
        )
        MovieInfo(
            name = stringResource(id = R.string.release_date),
            value = movieDetails?.releaseDate.toString()
        )
    }
}

@Composable
fun MovieInfo(
    name: String, value: String
) {
    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.subtitle2.copy(fontSize = 13.sp, letterSpacing = 1.sp),
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.SemiBold),
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovieFieldsPreview() {
    MovieInfoContent(
        movieDetails = MovieDetails(
            id = 1,
            title = "Filme",
            genres = listOf("Aventura", "comedia"),
            overview = null,
            backgroundPathUrl = null,
            releaseDate = null,
            voteAverage = 7.5,
            duration = 90,
            voteCount = 100
        ),
        modifier = Modifier.fillMaxWidth()
    )
}