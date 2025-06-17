package com.mobile.chatapp.persentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SnackBarSuccess(snackbarHostState: SnackbarHostState) {


    Box(Modifier.fillMaxSize()
        .padding(top = 15.dp), contentAlignment = Alignment.TopCenter){
        SnackbarHost(hostState = snackbarHostState,Modifier.padding(top = 15.dp)) { data ->
            androidx.compose.material3.Snackbar(
                snackbarData = data,
                containerColor = MaterialTheme.colorScheme.primary
            )
        }
    }

}

@Composable
fun SnackBarError(snackbarHostState: SnackbarHostState) {


    Box(Modifier.fillMaxSize()
        .padding(top = 15.dp), contentAlignment = Alignment.TopCenter){
        SnackbarHost(hostState = snackbarHostState,Modifier.padding(top = 15.dp)) { data ->
            androidx.compose.material3.Snackbar(
                snackbarData = data,
                containerColor = MaterialTheme.colorScheme.error,
            )
        }
    }

}


@Composable
fun ShimmerText(
    modifier: Modifier = Modifier,
    content: @Composable (Brush) -> Unit
) {
    val transition = rememberInfiniteTransition(label = "Shimmer")
    val shimmerTranslateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing)
        ),
        label = "ShimmerTranslate"
    )

    val shimmerColors = listOf(
        Color.Gray.copy(alpha = 1f),
        Color.White.copy(alpha = 0.9f),
        Color.Gray.copy(alpha = 1f)
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(shimmerTranslateAnim, shimmerTranslateAnim),
        end = Offset(shimmerTranslateAnim + 200f, shimmerTranslateAnim + 200f)
    )

    Box(modifier = modifier) {
        content(brush)
    }
}


