package com.mobile.chatapp.persentation.ui.screen.chat

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.chatapp.R
import com.mobile.chatapp.persentation.ui.theme.AppTheme
import com.mobile.chatapp.persentation.ui.theme.zohoBold
import com.mobile.chatapp.persentation.ui.theme.zohoLight
import com.mobile.chatapp.persentation.ui.theme.zohoRegular

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreen(navController: NavController){
    Scaffold (Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                windowInsets = TopAppBarDefaults.windowInsets,
                modifier = Modifier.clickable(){

                },
                title = {
                    Row(

                    ){
                        Box(
                            Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(color = MaterialTheme.colorScheme.onSurface)
                        ) {

                        }
                        Spacer(Modifier.width(10.dp))
                        Column() {
                            Text(
                                text = "PersonName",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = zohoBold,
                                    color = MaterialTheme.colorScheme.onSurface,
                
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "online",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = zohoLight,
                                    color = MaterialTheme.colorScheme.onSurface
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_24),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {

                        Icon(
                            painter = painterResource(R.drawable.baseline_call_24),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onSurface
                        )

                    }

                    IconButton(onClick = {}) {

                        Icon(
                            painter = painterResource(R.drawable.baseline_more_vert_24),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onSurface
                        )

                    }
                }
            )
        },
        content = {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.img_bg),
                contentDescription = "img_bg",
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)))
        },
        bottomBar = {
            Row(
            ) {
                OutlinedTextField(
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = zohoRegular
                    ),
                    placeholder = {
                        Text("guest@gmail.com",
                            fontSize = 13.sp,
                            fontFamily = zohoRegular,
                            color = Color.Gray.copy(alpha = 0.7f),
                            fontWeight = FontWeight.W500,
                            textAlign = TextAlign.Center,
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    value = "forgetPasswordVisiblilty",
                    onValueChange = {
//                        forgetPassword = it
                    },
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    maxLines = 1,
                    colors = OutlinedTextFieldDefaults.colors(
//                            focusedContainerColor = Color.Gray.copy(alpha = 0.5f),
                        focusedBorderColor = Color.Gray.copy(alpha = 0.5f),
//                            disabledContainerColor = Color.Gray.copy(alpha = 0.5f),
                        disabledBorderColor = Color.Gray.copy(alpha = 0.5f),
//                            cursorColor = if (isDark) AppThemeColor else Color.Red,
                    ),

                    )
            }
        }
    )
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ChatDarkScreen(){
    AppTheme {
        ChatScreen(rememberNavController())
    }
}

@Preview(showBackground = true,)
@Composable
fun ChatLightScreen(){
    AppTheme {
        ChatScreen(rememberNavController())
    }
}