package com.mobile.chatapp.persentation.ui.screen.duo

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mobile.chatapp.R
import com.mobile.chatapp.persentation.navigation.appnav.AppRoutes
import com.mobile.chatapp.persentation.ui.theme.AppTheme
import com.mobile.chatapp.persentation.ui.theme.zohoExtraBold
import com.mobile.chatapp.persentation.ui.theme.zohoMedium
import com.mobile.chatapp.persentation.ui.theme.zohoRegular
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mobile.chatapp.persentation.ui.screen.auth.viewmodel.AuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import com.mobile.chatapp.data.dto.DuoFriendsData

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DuoScreen(
    navController: NavController,
    duoViewModel: DuoViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {

    val friendsData by duoViewModel.friendProfiles.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_animation))
    val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)


    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            duoViewModel.getFriendProfiles(authViewModel.getAuthToken())
            Log.d("LogData", "Data from Ui -> $friendsData")
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "AppName",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontFamily = zohoExtraBold,
                        )
                    )
                },
                navigationIcon = {},
                actions = {
                    IconButton(onClick = {}) {

                        Icon(
                            painter = painterResource(R.drawable.baseline_qr_code_scanner_24),
                            contentDescription = ""
                        )

                    }

                    IconButton(onClick = {}) {

                        Icon(
                            painter = painterResource(R.drawable.baseline_more_vert_24),
                            contentDescription = ""
                        )

                    }
                },
//            colors = TopAppBarColors(containerColor = Color.Blue, scrolledContainerColor = Color.Blue, navigationIconContentColor = Color.Blue, titleContentColor = Color.Blue, actionIconContentColor = Color.Blue),
            )
        },
        content = {



            if (friendsData?.isEmpty() == true) {
                Column(Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center) {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LottieAnimation(
                            composition = composition,
                            progress = { progress },
                            modifier = Modifier.size(250.dp)
                        )
                    }
                }
            }
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .height(45.dp)
                            .background(
                                color = Color.Gray.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clickable {
                                navController.navigate(AppRoutes.SEARCH_SCREEN)
                            },
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Outlined.Search, contentDescription = "")
                            Spacer(Modifier.width(5.dp))
                            Text(
                                text = "Search",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontFamily = zohoMedium,
                                )
                            )
                        }
                    }
                }
                if (friendsData?.isNotEmpty() == true){
                    items(friendsData.orEmpty()) {friend ->
                        Log.d("LogData-Friend","Friend Data -> $friend")
                        ChatItem(friend)
                    }
                }
            }
        }
    )
}

@Composable
fun ChatItem(friend: DuoFriendsData) {

    Box(
        Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 15.dp, vertical = 5.dp)

    ) {

        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {

            Box(
                Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.onSurface)
            ) {

            }
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 15.dp),
                    Arrangement.SpaceEvenly
                ) {

                    Text(
                        text = friend.friendName,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = zohoMedium,
                        ),
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.onSurface,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "You: Ok",
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = zohoRegular,
                        )
                    )
                }

                Column(
                    Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceEvenly

                ) {
                    Text(
                        text = "10:45 PM",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = zohoMedium,
                        ),
                        textAlign = TextAlign.Center,
                        color = colorResource(R.color.card_text_color)
                    )
                    Row{
                        Box(
                            Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(color = MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                textAlign = TextAlign.Center,
                                text = friend.unreadMsg.toString(),
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    fontFamily = zohoMedium,
                                ),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ChatPreview() {
    AppTheme {
//        ChatItem()
        DuoScreen(navController = NavController(LocalContext.current))
    }
}