package com.mobile.chatapp.persentation.ui.screen.duo
import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobile.chatapp.R
import com.mobile.chatapp.persentation.ui.theme.zohoExtraBold
import com.mobile.chatapp.persentation.ui.theme.zohoMedium
import com.mobile.chatapp.persentation.ui.theme.zohoRegular

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DuoScreen(navController: NavController){


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(
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

                    Icon(painter = painterResource(R.drawable.baseline_qr_code_scanner_24), contentDescription = "")

                }

                IconButton(onClick = {}) {

                    Icon(painter = painterResource(R.drawable.baseline_more_vert_24), contentDescription = "")

                }
            },
//            colors = TopAppBarColors(containerColor = Color.Blue, scrolledContainerColor = Color.Blue, navigationIconContentColor = Color.Blue, titleContentColor = Color.Blue, actionIconContentColor = Color.Blue),
            )
                 },
        content = {

            LazyColumn (Modifier.fillMaxSize().padding(it)){
                item {

                    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                        .height(45.dp)
                        .background(color = Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.CenterStart,
                    ){
                        Row (Modifier.fillMaxWidth().padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically){
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


                items(20) {
                    Spacer(Modifier.height(10.dp))
                    ChatItem()
                }

            }
        }
    )
}



@Composable
fun ChatItem(){


    Box(Modifier.fillMaxWidth()
        .height(55.dp)
        .padding(horizontal = 10.dp)){

        Row (Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically){

            Box(Modifier.size(50.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.onSurface)){

            }

            Spacer(Modifier.width(7.dp))

            Row (Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                Column (Modifier.fillMaxHeight(),Arrangement.SpaceEvenly){

                    Text(
                        text = "Kinston",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = zohoMedium,
                        )
                    )



                    Text(
                        text = "You: Ok",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = zohoRegular,
                        )
                    )

                }

                Column (Modifier.fillMaxHeight(),Arrangement.SpaceEvenly,Alignment.End){

                    Text(
                        text = "10:45 PM",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = zohoMedium,
                        )
                    )


                    Box(Modifier.clip(CircleShape).background(color = MaterialTheme.colorScheme.primaryContainer), contentAlignment = Alignment.Center){
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "4",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontFamily = zohoMedium,
                            )
                        )
                    }

                }
            }

        }

    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {

}