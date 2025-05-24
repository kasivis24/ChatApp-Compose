package com.mobile.chatapp.persentation.ui.screen.duo.search

import android.Manifest
import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mobile.chatapp.R
import com.mobile.chatapp.data.dto.DuoChatData
import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.data.dto.SearchData
import com.mobile.chatapp.persentation.ui.screen.auth.viewmodel.AuthViewModel
import com.mobile.chatapp.persentation.ui.screen.notify.NotifyViewModel
import com.mobile.chatapp.persentation.ui.theme.AppTheme
import com.mobile.chatapp.persentation.ui.theme.zohoMedium
import com.mobile.chatapp.persentation.ui.theme.zohoRegular
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController,searchViewModel: SearchViewModel = hiltViewModel(),authViewModel: AuthViewModel = hiltViewModel(),notifyViewModel: NotifyViewModel = hiltViewModel()){

    var searchQuery by rememberSaveable { mutableStateOf("") }

    val searchedProfileDataList by searchViewModel.searchProfileList.observeAsState()

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_animation))

    val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)

    val coroutineScope = rememberCoroutineScope()

    val zoneId = TimeZone.getTimeZone("Asia/Kolkata").toZoneId()
    val currentTime = rememberSaveable { ZonedDateTime.now(zoneId) }

    val day = currentTime.format(DateTimeFormatter.ofPattern("EEEE")) // e.g., Monday
    val date = currentTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) // e.g., 22 May 2025
    val time = currentTime.format(DateTimeFormatter.ofPattern("hh:mm a"))

    Scaffold {
        Box(Modifier.fillMaxSize().padding(it)){
            Column (Modifier.fillMaxSize()){


                Row (Modifier.fillMaxWidth()){
                    OutlinedTextField(
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = zohoRegular,
                        ),
                        placeholder = {
                            Text("Search Here",
                                fontFamily = zohoMedium,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W500,
                                textAlign = TextAlign.Center,
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 15.dp),
                        value = searchQuery,
                        trailingIcon = {
                            IconButton(onClick = {}) {
                                Image(
                                    modifier = Modifier.size(23.dp),
                                    painter = painterResource(R.drawable.baseline_mic_24),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface)
                                )
                            }
                        },
                        leadingIcon = {
                            Row {

                                IconButton(onClick = {
                                    navController.popBackStack()
                                }) {
                                    Image(
                                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                                        modifier = Modifier.size(23.dp),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface)
                                    )
                                }
                            }
                        },
                        onValueChange = {
                            searchViewModel.searchProfile(it,authViewModel.getAuthToken())
                            searchQuery = it
                        },
                        shape = RoundedCornerShape(13.dp),
                        singleLine = true,
                        maxLines = 1,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Gray.copy(alpha = 0.3f),
                            disabledBorderColor = Color.Gray.copy(alpha = 0.3f),
                        ),
                    )
                }


                if (!searchQuery.isEmpty()){
                    LazyColumn (Modifier.fillMaxSize()){
                        searchedProfileDataList?.let {searchedList->
                            items(searchedList){ profileData ->
                                ProfileItem(profileData, onClick = {receiverId ->
                                    coroutineScope.launch {
                                        searchViewModel.sendFollowRequest(authViewModel.getAuthToken(),receiverId,day, date, time)
                                    }
                                },notifyViewModel)
                            }
                        }
                    }
                }else {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        LottieAnimation(
                            composition = composition,
                            progress = { progress },
                            modifier = Modifier.size(250.dp)
                        )
                    }
                }


            }
        }
    }

}


@Composable
fun ProfileItem(profileData: SearchData,onClick: (String)-> Unit,notifyViewModel: NotifyViewModel){

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val halfWidth = screenWidth / 3


    Box (Modifier.fillMaxWidth().height(75.dp).padding(bottom = 10.dp, start = 10.dp),){

        Row (Modifier.fillMaxSize().padding(5.dp), verticalAlignment = Alignment.CenterVertically){

            Box(Modifier.size(50.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.onSurface)
                ){

            }

            Box(Modifier.fillMaxSize().padding(horizontal = 15.dp)){

                Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,){

                    Text(
                        "${profileData.name}",
                        fontFamily = zohoMedium,
                        fontSize = 16.sp,
                        maxLines = 1,
                        minLines = 1,
                        fontWeight = FontWeight.W500,
                        modifier = Modifier.width(width = halfWidth),
                        overflow = TextOverflow.Ellipsis,
                    )

                    Text("${profileData.mail}",
                        fontFamily = zohoMedium,
                        fontSize = 14.sp,
                        maxLines = 1,
                        minLines = 1,
                        fontWeight = FontWeight.W500,
                        color = Color.Gray.copy(alpha = 0.6f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(halfWidth),
                        overflow = TextOverflow.Ellipsis,
                    )


                }


                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd){
                    when (profileData.statusCode){
                        1 -> StatusCodeOne(profileData,
                        onDecline = {
                            notifyViewModel.declineRequest(it)
                        },
                        onAccept = {requestId,senderId,receiverid->
                            // remove the request from the request table and move the data to DuoChat
                            notifyViewModel.acceptRequest(requestId,DuoChatData(senderId,receiverid,true))
                        })
                        2 -> StatusCodeTwo(profileData,
                        onClick = {
                            onClick(it)
                        })
                        3 -> StatusCodeThree()

                        4 -> StatusCodeFour()

                    }
                }



            }

        }

    }
}

@Composable
fun StatusCodeOne(searchData: SearchData,onDecline: (String)-> Unit,onAccept: (String,String,String)-> Unit){
    Row {
        IconButton(onClick = {
            onDecline(searchData.requestId)
        }) {
            Icon(painter = painterResource(R.drawable.ic_cancel), tint = MaterialTheme.colorScheme.error, contentDescription = "ic_cancel")
        }
        IconButton(onClick = {
            onAccept(searchData.requestId,searchData.senderId,searchData.receiverId)
        }) {
            Icon(painter = painterResource(R.drawable.ic_check), tint = MaterialTheme.colorScheme.primary, contentDescription = "ic_cancel")
        }
    }
}

@Composable
fun StatusCodeTwo(profileData: SearchData,onClick: (String) -> Unit){
    IconButton(onClick = {
            onClick(profileData.userId)
    }) {
        Icon(painter = painterResource(R.drawable.ic_add), contentDescription = "Add")
    }
}

@Composable
fun StatusCodeThree(){
    IconButton(onClick = {}) {
        Icon(painter = painterResource(R.drawable.ic_time), tint = colorResource(R.color.card_text_color), contentDescription = "ic_time")
    }
}

@Composable
fun StatusCodeFour(){
    IconButton(onClick = {}) {
        Icon(painter = painterResource(R.drawable.ic_msg), tint = colorResource(R.color.card_text_color), contentDescription = "ic_time")
    }
}


@Preview(showBackground = true,)
@Composable
fun PreviewSearchScreenLight(){
    AppTheme {
//        val navController = rememberNavController()
//        SearchScreen(navController)
//        StatusCodeOne()
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewSearchScreenDark(){
    AppTheme {
//        val navController = rememberNavController()
//        SearchScreen(navController)
//        StatusCodeOne()
    }
}
