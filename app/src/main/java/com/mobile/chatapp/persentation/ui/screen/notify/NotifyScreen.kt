package com.mobile.chatapp.persentation.ui.screen.notify

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.chatapp.R
import com.mobile.chatapp.data.dto.DuoChatData
import com.mobile.chatapp.data.dto.DuoRequestData
import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.persentation.navigation.appnav.AppRoutes
import com.mobile.chatapp.persentation.ui.screen.auth.viewmodel.AuthViewModel
import com.mobile.chatapp.persentation.ui.screen.duo.ChatItem
import com.mobile.chatapp.persentation.ui.screen.team.TeamScreen
import com.mobile.chatapp.persentation.ui.theme.AppTheme
import com.mobile.chatapp.persentation.ui.theme.zohoBold
import com.mobile.chatapp.persentation.ui.theme.zohoExtraBold
import com.mobile.chatapp.persentation.ui.theme.zohoMedium
import com.mobile.chatapp.persentation.ui.theme.zohoRegular
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotifyScreen(navController: NavController) {


    val tabTitles = listOf("Duo Requests", "Team Requests")
    val pagerState = rememberPagerState(pageCount = { tabTitles.size })
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Requests",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontFamily = zohoBold,
                        )
                    )
                },
                navigationIcon = {},
                actions = {
                },
            )
        },
        content = {

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    TabRow(
                        divider = {},
                        selectedTabIndex = pagerState.currentPage,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                                height = 2.dp,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        },
                    ) {

                        tabTitles.forEachIndexed { index, title ->
                            Tab(
                                unselectedContentColor = Color.Gray.copy(alpha = 0.5f),
                                selectedContentColor = MaterialTheme.colorScheme.secondary,
                                selected = pagerState.currentPage == index,
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                },
                                text = {
                                    Text(
                                        title,
                                        fontFamily = zohoMedium,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.W700,
                                    )
                                }
                            )
                        }
                    }

                }


                HorizontalPager(
                    state = pagerState, modifier = Modifier.weight(1f)
                ) { page ->
                    when (page) {
                        0 -> DuoRequest()
                        1 -> TeamRequest()
                    }


                }

            }

        }
    )
}


@Composable
fun DuoRequest(notifyViewModel: NotifyViewModel = hiltViewModel(),authViewModel: AuthViewModel = hiltViewModel()){


    val requestProfiles by notifyViewModel.requestProfiles.observeAsState()

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect (Unit){
       coroutineScope.launch(Dispatchers.IO){
           notifyViewModel.getRequestProfiles(authViewModel.getAuthToken())
           Log.d("LogData","Data from Ui ${requestProfiles}")
       }
    }


    Box(Modifier.fillMaxSize()){
        LazyColumn (Modifier.fillMaxSize()){
            requestProfiles?.let {
                items(it){profileData ->
                    RequestCard(
                        profileData,
                        onDecline = {
                            notifyViewModel.declineRequest(it)
                        },
                        onAccept = {requestId,senderId,receiverId->
                            notifyViewModel.acceptRequest(requestId, DuoChatData(senderId,receiverId,true))
                            Log.d("LogData","SenderID -> ${senderId}")
                            Log.d("LogData","Reciver -> ${receiverId}")
                            Log.d("LogData","RequestID -> ${requestId}")
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun TeamRequest(){
    Box(Modifier.fillMaxSize()){

    }
}


@Composable
fun RequestCard(profileData: DuoRequestData,onDecline : (String)-> Unit,onAccept : (String,String,String)-> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
        ),
        shape = RoundedCornerShape(10.dp)

    ) {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(R.drawable.icon_profile),
                    contentDescription = "profile_image",
                    modifier = Modifier
                        .size(70.dp),
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "${profileData.name}",
                        fontSize = 18.sp,
                        style = TextStyle(fontFamily = zohoBold),
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text(
                        text = "${profileData.mail}",
                        fontSize = 16.sp,
                        style = TextStyle(fontFamily = zohoMedium),
                        color = colorResource(R.color.card_text_color)
                    )
                    Text(
                        text = "${profileData.date}, ${profileData.time}",
                        fontSize = 14.sp,
                        style = TextStyle(fontFamily = zohoRegular),
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    border = BorderStroke(1.dp, colorResource(R.color.card_text_color)),
                    onClick = {
                        onDecline(profileData.requestId)
                    }) {
                    Text(
                        text = "Cancel",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        style = TextStyle(fontFamily = zohoRegular),
                        color = colorResource(R.color.card_text_color)
                    )
                }
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    border = BorderStroke(1.dp, colorResource(R.color.card_text_color)),
                    onClick = {
                        onAccept(profileData.requestId,profileData.senderId,profileData.receiverId)
                    }) {
                    Text(
                        text = "Accept",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        style = TextStyle(fontFamily = zohoRegular),
                        color = colorResource(R.color.card_text_color)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RequestCardPreview() {
    AppTheme {
        val navController = rememberNavController()
        NotifyScreen(navController)
//        RequestCard()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RequestCardPreviewDark() {
    AppTheme {
        val navController = rememberNavController()
        NotifyScreen(navController)
//        RequestCard()
    }
}