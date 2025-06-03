package com.mobile.chatapp.persentation.ui.screen.chat

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreen(navController: NavController) {
    val messageList = remember { List(20) { "Message $it" } }
    val textState = rememberSaveable { mutableStateOf("") }
    val listState = rememberLazyListState()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.img_bg),
            contentDescription = "img_bg",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                    alpha = 0.3f
                )
            )
        )
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    modifier = Modifier.clickable() {

                    },
                    title = {
                        Row(

                        ) {
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
            content = { padding ->
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 8.dp),
                    reverseLayout = true
                ) {


                }
            },
            bottomBar = {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .imePadding(),
                    shape = RoundedCornerShape(10.dp),
                    tonalElevation = 4.dp,
                    color = Color.White.copy(alpha = 0.0f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MessageInputField(
                            text = textState.value, onTextChange = {
                                textState.value = it
                            },
                        )

//                        BasicTextField(
//                            textStyle = TextStyle(
//                                fontSize = 14.sp,
//                                fontFamily = zohoRegular,
//                                textAlign = TextAlign.Center,
//                                ),
//                            placeholder = {
//                                Text(
//                                    "Message",
//                                    fontSize = 18.sp,
//                                    fontFamily = zohoRegular,
//                                    color = MaterialTheme.colorScheme.outline,
//                                    textAlign = TextAlign.Center,
//                                )
//                            },
//
//                            modifier = Modifier
//                                .weight(1f)
//                                .height(45.dp),
//                            value = "",
//                            onValueChange = {
////
//                            },
//                            shape = RoundedCornerShape(20.dp),
//
//                            colors = TextFieldDefaults.colors(
//                                focusedIndicatorColor = Color.Transparent,
//                                unfocusedIndicatorColor = Color.Transparent,
//                                disabledIndicatorColor = Color.Transparent
//                            )
//
//                        )
                    }
                }
            }
        )
    }
}

@Composable
fun MessageInputField(
    text: String,
    onTextChange: (String) -> Unit,
) {
    Row(Modifier.fillMaxWidth()
        .padding(bottom = 8.dp)) {
        Box(
            modifier = Modifier
                .border(0.5.dp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),RoundedCornerShape(50.dp))
                .weight(1f)
                .background(MaterialTheme.colorScheme.surfaceContainerLow.copy(alpha = 0.75f), RoundedCornerShape(50)) // Your dark rounded shape
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            if (text.isEmpty()) {
                Text(
                    text = "Message",
                    fontSize = 18.sp,
                    fontFamily = zohoRegular,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }

            BasicTextField(
                value = text,
                onValueChange = onTextChange,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = zohoRegular,
                    color = MaterialTheme.colorScheme.onSurface),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.width(5.dp))

        IconButton(
            modifier = Modifier.size(50.dp),
            onClick = {

            },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )

        ) {
            Icon(
                painter = painterResource(R.drawable.ic_send),
                contentDescription = "Send",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(26.dp)
            )
        }


    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ChatDarkScreen() {
    AppTheme {
        ChatScreen(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun ChatLightScreen() {
    AppTheme {
        ChatScreen(rememberNavController())
    }
}