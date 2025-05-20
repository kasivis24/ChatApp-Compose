package com.mobile.chatapp.persentation.ui.screen.duo.search

import android.Manifest
import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.chatapp.R
import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.persentation.ui.theme.AppTheme
import com.mobile.chatapp.persentation.ui.theme.zohoMedium
import com.mobile.chatapp.persentation.ui.theme.zohoRegular
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController,searchViewModel: SearchViewModel = hiltViewModel()){

    var searchQuery by rememberSaveable { mutableStateOf("") }

    val searchedProfileDataList by searchViewModel.searchProfileList.collectAsState()

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
                            searchViewModel.searchProfile(it)
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

                        items(searchedProfileDataList){profileData ->
                            ProfileItem(profileData)
                        }

                    }
                }else {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Text("Please put your Query to Find the Profile",
                            fontFamily = zohoMedium,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500,
                            textAlign = TextAlign.Center,
                        )
                    }
                }


            }
        }
    }

}


@Composable
fun ProfileItem(profileData: ProfileData){

    Box (Modifier.fillMaxWidth().height(75.dp).padding(bottom = 10.dp, start = 10.dp),){

        Row (Modifier.fillMaxSize().padding(5.dp), verticalAlignment = Alignment.CenterVertically){

            Box(Modifier.size(50.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.onSurface)
                ){



            }

            Box(Modifier.fillMaxSize().padding(horizontal = 15.dp)){

                Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,){

                    Text("${profileData.name}",
                        fontFamily = zohoMedium,
                        fontSize = 16.sp,
                        maxLines = 1,
                        minLines = 1,
                        fontWeight = FontWeight.W500,
                        textAlign = TextAlign.Center,
                    )

                    Text("${profileData.mail}",
                        fontFamily = zohoMedium,
                        fontSize = 14.sp,
                        maxLines = 1,
                        minLines = 1,
                        fontWeight = FontWeight.W500,
                        color = Color.Gray.copy(alpha = 0.6f),
                        textAlign = TextAlign.Center,
                    )


                }


                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd){
                    IconButton(onClick = {}) {
                        Icon(painter = painterResource(R.drawable.baseline_add_circle_outline_24), contentDescription = "Add")
                    }
                }



            }

        }

    }
}


@Preview(showBackground = true,)
@Composable
fun PreviewSearchScreenLight(){
    AppTheme {
//        val navController = rememberNavController()
//        SearchScreen(navController)
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewSearchScreenDark(){
    AppTheme {
//        val navController = rememberNavController()
//        SearchScreen(navController)
    }
}
