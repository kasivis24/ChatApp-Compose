package com.mobile.chatapp.persentation.ui.screen.chat

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mobile.chatapp.R
import com.mobile.chatapp.persentation.ui.theme.AppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreen(){
    Scaffold (Modifier.fillMaxSize()){
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.img_bg),
            contentDescription = "img_bg",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurfaceVariant))
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ChatDarkScreen(){
    AppTheme {
        ChatScreen()
    }
}

@Preview(showBackground = true,)
@Composable
fun ChatLightScreen(){
    AppTheme {
        ChatScreen()
    }
}