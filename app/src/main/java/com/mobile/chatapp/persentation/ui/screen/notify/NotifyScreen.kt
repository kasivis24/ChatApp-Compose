package com.mobile.chatapp.persentation.ui.screen.notify

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.chatapp.R
import com.mobile.chatapp.persentation.navigation.appnav.AppRoutes
import com.mobile.chatapp.persentation.ui.screen.duo.ChatItem
import com.mobile.chatapp.persentation.ui.screen.team.TeamScreen
import com.mobile.chatapp.persentation.ui.theme.AppTheme
import com.mobile.chatapp.persentation.ui.theme.zohoBold
import com.mobile.chatapp.persentation.ui.theme.zohoExtraBold
import com.mobile.chatapp.persentation.ui.theme.zohoMedium
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
fun DuoRequest(){
    Box(Modifier.fillMaxSize()){
        LazyColumn (Modifier.fillMaxSize()){

        }
    }
}


@Composable
fun TeamRequest(){
    Box(Modifier.fillMaxSize()){

    }
}

@Composable
fun RequestCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

        }
    }
}


@Preview(showBackground = true)
@Composable
fun RequestCardPreview() {
    AppTheme {
        val navController = rememberNavController()
        NotifyScreen(navController)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RequestCardPreviewDark() {
    AppTheme {
        val navController = rememberNavController()
        NotifyScreen(navController)
    }
}