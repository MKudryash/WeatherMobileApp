package com.example.weathermobileapp.Presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun ConditionIcon(icon: String, height: Dp, width: Dp) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data("https:$icon")
            .size(Size.ORIGINAL).build()
    ).state

    if (imageState is AsyncImagePainter.State.Error) {
        Box(
            modifier = Modifier
                .width(width)
                .height(height),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    if (imageState is AsyncImagePainter.State.Success) {
        Image(
            modifier = Modifier
                .width(width)
                .height(height),
            painter = imageState.painter,
            contentDescription = icon,
            contentScale = ContentScale.FillWidth
        )
    }
}