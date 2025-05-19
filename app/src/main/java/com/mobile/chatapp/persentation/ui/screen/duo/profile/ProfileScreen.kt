package com.mobile.chatapp.persentation.ui.screen.duo.profile

import android.content.res.Configuration
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.chatapp.R
import com.mobile.chatapp.persentation.ui.theme.AppTheme
import com.mobile.chatapp.persentation.ui.theme.zohoBold
import com.mobile.chatapp.persentation.ui.theme.zohoMedium
import com.mobile.chatapp.persentation.ui.theme.zohoRegular

data class MediaItem(val name: String, val icon: Int)

val mediaItems = listOf(
    MediaItem("Images", R.drawable.icon_image),
    MediaItem("Audio", R.drawable.icon_audio),
    MediaItem("Video", R.drawable.icon_video_file),
    MediaItem("Files", R.drawable.icon_file),
    MediaItem("Links", R.drawable.icon_link),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(10.dp),
                title = {
                    Text(
                        text = "Saved Name",
                        fontSize = 18.sp,
                        style = TextStyle(fontFamily = zohoBold),
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 30.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_menu),
                            contentDescription = "Menu",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                ,
                contentAlignment = Alignment.TopCenter
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon_profile),
                        contentDescription = "Profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(120.dp)
                            .border(2.dp, MaterialTheme.colorScheme.onSurface, CircleShape)
                            .clip(CircleShape)
                    )
                    Text(
                        text = "User Name",
                        fontSize = 16.sp,
                        style = TextStyle(fontFamily = zohoMedium),
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.padding(top = 20.dp)
                            .fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_call),
                            contentDescription = "Call",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(30.dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.icon_video),
                            contentDescription = "Call",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(35.dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.icon_mute),
                            contentDescription = "Call",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        ),
                        modifier = Modifier.fillMaxWidth()
                            .height(160.dp)
                            .padding(top = 20.dp)
                            .padding(horizontal = 20.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "phone",
                                style = TextStyle(fontFamily = zohoRegular),
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = 15.sp
                            )
                            Text(
                                text = "+919876543210",
                                style = TextStyle(fontFamily = zohoMedium),
                                color = colorResource(R.color.card_text_color),
                                fontSize = 16.sp
                            )
                            Text(
                                text = "name",
                                style = TextStyle(fontFamily = zohoRegular),
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(top = 10.dp)
                            )
                            Text(
                                text = "@aavf_goqejp44",
                                style = TextStyle(fontFamily = zohoMedium),
                                color = colorResource(R.color.card_text_color),
                                fontSize = 16.sp
                            )
                        }
                    }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        ),
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 20.dp)
                            .padding(horizontal = 20.dp),
                        shape = RoundedCornerShape(16.dp)
                    ){
                        LazyVerticalGrid(
                            columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
                            modifier = Modifier.padding(20.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalArrangement = Arrangement.Center,
                            userScrollEnabled = false
                        ) {
                            items(mediaItems){
                                Row(
                                    modifier = Modifier
                                        .padding(5.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)

                                ){
                                    Icon(
                                        painter = painterResource(id = it.icon),
                                        contentDescription = it.name,
                                        modifier = Modifier.size(22.dp),
                                        tint = colorResource(R.color.card_text_color)
                                    )
                                    Text(
                                        text = it.name,
                                        style = TextStyle(fontFamily = zohoMedium),
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = 18.sp
                                    )
                                }
                            }
                        }
                    }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        ),
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 20.dp)
                            .padding(horizontal = 20.dp),
                        shape = RoundedCornerShape(16.dp)
                    ){
                        Column(
                            modifier = Modifier.padding(18.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)

                            ){
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_team),
                                    contentDescription = "Groups",
                                    modifier = Modifier.size(22.dp),
                                    tint = colorResource(R.color.card_text_color)
                                )
                                Text(
                                    text = "Groups",
                                    style = TextStyle(fontFamily = zohoMedium),
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontSize = 18.sp
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)

                            ){
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_pin),
                                    contentDescription = "Groups",
                                    modifier = Modifier.size(22.dp),
                                    tint = colorResource(R.color.card_text_color)
                                )
                                Text(
                                    text = "Pinned Message",
                                    style = TextStyle(fontFamily = zohoMedium),
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreviewLight(){
    AppTheme {
        ProfileScreen()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProfileScreenPreviewDark(){
    AppTheme {
        ProfileScreen()
    }
}