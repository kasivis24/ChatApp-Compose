package com.mobile.chatapp.persentation.ui.screen.duo.profile

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.mobile.chatapp.persentation.ui.theme.zohoLight
import com.mobile.chatapp.persentation.ui.theme.zohoSemiBold

data class MediaItem(val name: String, val icon: Int)

val mediaItems = listOf(
    MediaItem("Images", R.drawable.icon_image),
    MediaItem("Audio", R.drawable.icon_audio),
    MediaItem("Video", R.drawable.icon_video_file),
    MediaItem("Files", R.drawable.icon_file),
    MediaItem("Links", R.drawable.icon_link)
)

data class ScheduledMessage(val message: String, val date: String, val time: String)

val scheduledMessages = listOf(
    ScheduledMessage("You: Hello", "15-05-2025", "12:00 AM"),
    ScheduledMessage("You: Hey, Gwen!", "26-05-2026", "01:30 PM"),
    ScheduledMessage(
        "You: Miss You 3000. When We meet again," +
                " I'll tell you how much I missed you..", "25-05-2025", "04:00 PM"
    ),
    ScheduledMessage("You: Waiting for you !", "13-05-2026", "01:30 PM"),
    ScheduledMessage("You: Hey, Gwen!", "26-05-2026", "01:30 PM"),
    ScheduledMessage("You: Hey, Gwen!", "26-05-2026", "01:30 PM"),
    ScheduledMessage("You: Hey, Gwen!", "26-05-2026", "01:30 PM"),
    ScheduledMessage("You: Hey, Gwen!", "26-05-2026", "01:30 PM"),
    ScheduledMessage("You: Hey, Gwen!", "26-05-2026", "01:30 PM"),
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val lazyListState = rememberLazyListState()
    val appName = stringResource(R.string.app_name)
    val collapsedFraction = scrollBehavior.state.collapsedFraction
    val context = LocalContext.current
    var isScheduledMessagesVisible by rememberSaveable { mutableStateOf(false) }
    val bottomSheetForScheduledMessage = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                scrollBehavior = scrollBehavior,
                modifier = Modifier.padding(0.dp),
                title = {
                    if (collapsedFraction > 0f) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_profile),
                                contentDescription = "Profile Small",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(40.dp)
                                    .border(2.dp, MaterialTheme.colorScheme.onSurface, CircleShape)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "UserName", fontSize = 18.sp,
                                style = TextStyle(fontFamily = zohoBold),
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(start = 10.dp),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                if (isScheduledMessagesVisible) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            isScheduledMessagesVisible = !isScheduledMessagesVisible
                        },
                        sheetState = bottomSheetForScheduledMessage,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (true) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = "Scheduled Messages",
                                    fontSize = 22.sp,
                                    color = colorResource(R.color.card_text_color),
                                    style = TextStyle(fontFamily = zohoSemiBold)
                                )
                            }
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(top = 10.dp)
                            ) {
                                items(scheduledMessages) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(15.dp)
                                            .padding(horizontal = 20.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = it.message,
                                            modifier = Modifier
                                                .weight(0.7f),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            fontSize = 16.sp,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            style = TextStyle(fontFamily = zohoMedium)

                                        )
                                        Column(
                                            modifier = Modifier.weight(0.3f),
                                            horizontalAlignment = Alignment.End
                                        ) {
                                            Text(
                                                text = it.date,
                                                fontSize = 14.sp,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                style = TextStyle(fontFamily = zohoLight)
                                            )
                                            Text(
                                                text = it.time,
                                                textAlign = TextAlign.Center,
                                                fontSize = 14.sp,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                style = TextStyle(fontFamily = zohoLight)
                                            )
                                        }
                                    }
                                    HorizontalDivider(
                                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                            alpha = 0.5f
                                        ),
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(horizontal = 20.dp),

                                        )
                                }

                            }
                        } else {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No Scheduled Messages",
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    style = TextStyle(fontFamily = zohoBold),
                                )
                            }
                        }
                    }
                }
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        if (collapsedFraction == 0f) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 0.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.icon_profile),
                                    contentDescription = "Profile",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(120.dp)
                                        .border(
                                            2.dp,
                                            MaterialTheme.colorScheme.onSurface,
                                            CircleShape
                                        )
                                        .clip(CircleShape)
                                )
                                Text(
                                    text = "User Name",
                                    fontSize = 20.sp,
                                    style = TextStyle(fontFamily = zohoMedium),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(top = 10.dp)
                                )
                            }
                        }
                    }

                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .fillMaxWidth()
                        ) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(R.drawable.icon_call),
                                    contentDescription = "Call",
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(R.drawable.icon_video),
                                    contentDescription = "Video",
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.size(35.dp)
                                )
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(R.drawable.icon_search),
                                    contentDescription = "Search",
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(R.drawable.icon_mute),
                                    contentDescription = "Mute",
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                    }
                    item {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                                .padding(horizontal = 20.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(18.dp)
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Bio",
                                    style = TextStyle(fontFamily = zohoRegular),
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    fontSize = 18.sp,
                                )
                                Text(
                                    text = "I'm Using $appName",
                                    style = TextStyle(fontFamily = zohoMedium),
                                    color = colorResource(R.color.card_text_color),
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(top = 5.dp),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                )
                                Text(
                                    text = "Mail",
                                    style = TextStyle(fontFamily = zohoRegular),
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(top = 15.dp)
                                )
                                Text(
                                    text = "@aavf_goqejp44",
                                    style = TextStyle(fontFamily = zohoMedium),
                                    color = colorResource(R.color.card_text_color),
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(top = 5.dp),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                )
                            }
                        }
                    }
                    item {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                                .padding(horizontal = 20.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {

                            LazyVerticalGrid(
                                columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
                                modifier = Modifier
                                    .padding(20.dp)
                                    .heightIn(max = 250.dp),
//                                horizontalArrangement = Arrangement.Center,
//                                verticalArrangement = Arrangement.Center,
                                userScrollEnabled = false
                            ) {
                                items(mediaItems) {
                                    Row(
                                        modifier = Modifier
                                            .padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)

                                    ) {
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
                    }
                    item {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                                .padding(horizontal = 20.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .clickable {
                                            isScheduledMessagesVisible = !isScheduledMessagesVisible
                                        },
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),

                                    ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icon_clock),
                                        contentDescription = "scheduled",
                                        modifier = Modifier.size(22.dp),
                                        tint = colorResource(R.color.card_text_color)
                                    )
                                    Text(
                                        text = "Scheduled Messages",
                                        style = TextStyle(fontFamily = zohoMedium),
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = 18.sp
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)

                                ) {
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
                                Row(
                                    modifier = Modifier
                                        .padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)

                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icon_star),
                                        contentDescription = "Starred Messages",
                                        modifier = Modifier.size(22.dp),
                                        tint = colorResource(R.color.card_text_color)
                                    )
                                    Text(
                                        text = "Starred Messages",
                                        style = TextStyle(fontFamily = zohoMedium),
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = 18.sp
                                    )
                                }
                            }
                        }
                    }
                    item {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                                .padding(horizontal = 20.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)

                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icon_delete),
                                        contentDescription = "clear chat",
                                        modifier = Modifier.size(22.dp),
                                        tint = colorResource(R.color.card_text_color)
                                    )
                                    Text(
                                        text = "Clear Chat",
                                        style = TextStyle(fontFamily = zohoMedium),
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = 18.sp
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)

                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icon_block),
                                        contentDescription = "Block",
                                        modifier = Modifier.size(22.dp),
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                    Text(
                                        text = "Block",
                                        style = TextStyle(fontFamily = zohoMedium),
                                        color = MaterialTheme.colorScheme.error,
                                        fontSize = 18.sp
                                    )
                                }
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
fun ProfileScreenPreviewLight() {
    AppTheme {
        ProfileScreen()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProfileScreenPreviewDark() {
    AppTheme {
        ProfileScreen()
    }
}