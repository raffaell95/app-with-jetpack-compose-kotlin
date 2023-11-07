package com.raffa.movieapp.movie_popular_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.raffa.movieapp.R
import com.raffa.movieapp.core.presentation.components.common.AsyncImageUrl

@Composable
fun MovieItem(
    voteAverage: Double,
    imageUrl: String,
    id: Int,
    onClick: (id: Int) -> Unit
) {

    Box(){
        MovieRate(
            rate = voteAverage,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .zIndex(2f)
                .padding(start = 4.dp, bottom = 8.dp)
                .background(Color.Black)
        )
        Card(
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp)
                .padding(4.dp)
                .clickable {
                    onClick(id)
                },
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp
        ) {
            Box {
                AsyncImageUrl(
                    imageUrl = imageUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(Color.Black)
                        .clip(RoundedCornerShape(8.dp))
                )

            }

        }
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(
        voteAverage = 7.2,
        imageUrl = "",
        id = 1
    ) {}
}