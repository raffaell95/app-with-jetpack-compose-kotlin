package com.raffa.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.raffa.movieapp.R
import com.raffa.movieapp.core.presentation.components.common.AsyncImageUrl

@Composable
fun MovieDetailBackdropImage(
    backdropImageUrl: String, modifier: Modifier
) {

    Box(modifier = modifier){

        AsyncImageUrl(
            imageUrl = backdropImageUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Preview
@Composable
fun MovieDetailBackdropImagePreview(){
    MovieDetailBackdropImage(
        backdropImageUrl = "",
        modifier = Modifier.fillMaxWidth().height(200.dp)
    )
}