package com.mobile.chatapp.persentation.ui.screen.chat

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobile.chatapp.data.dto.MessageData
import com.mobile.chatapp.data.model.RouteChatData
import com.mobile.chatapp.data.remote.db.Database
import com.mobile.chatapp.data.remote.repo.FirebaseRepository
import com.mobile.chatapp.persentation.ui.screen.auth.viewmodel.AuthViewModel
import com.mobile.chatapp.persentation.ui.screen.duo.DuoViewModel
import com.mobile.chatapp.persentation.ui.theme.zohoMedium
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreen(navController: NavController,routeChatData: RouteChatData,duoViewModel: DuoViewModel = hiltViewModel(),authViewModel: AuthViewModel = hiltViewModel(),voiceRecorderViewModel: VoiceRecorderViewModel = viewModel()) {


    val messageDataList by duoViewModel.messageData.observeAsState(emptyList())

    val textState = rememberSaveable { mutableStateOf("") }
    val listState = rememberLazyListState()

    val context = LocalContext.current

    LaunchedEffect (Unit){
        duoViewModel.getDuoMessages(authViewModel.getAuthToken(),routeChatData.receiverId)
    }

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
                                    text = "${routeChatData.chatUserName}",
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

                        itemsIndexed(messageDataList){index,msgData->



                            val dismissState = rememberDismissState(
                                confirmStateChange = {
                                    if (it == DismissValue.DismissedToEnd) {
                                        Log.d("LogData","list length -> ${messageDataList?.size}  &&  ${messageDataList.get(index)}  Auth Token -> ${authViewModel.getAuthToken()}")
                                        duoViewModel.changeTextFieldType(true)
                                        duoViewModel.setReplyContents(if (authViewModel.getAuthToken().equals(messageDataList.get(index).senderId)) "You" else "${routeChatData.chatUserName}",messageDataList.get(index).msgContent,messageDataList.get(index).msgId)
                                        false
                                    } else {
                                        false
                                    }
                                }
                            )

                            SwipeToDismiss(
                                state = dismissState,
                                background = {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(horizontal = 20.dp),
                                        contentAlignment = Alignment.CenterStart
                                    ) {

                                    }
                                },
                                directions = setOf(DismissDirection.StartToEnd)
                            ){
                                MessageLayout(msgData,authViewModel.getAuthToken())
                            }


                        }


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
                            text = textState.value,
                            onTextChange = {
                                textState.value = it
                            },
                            onSendMsg = {
                                val zoneId = TimeZone.getTimeZone("Asia/Kolkata").toZoneId()
                                val currentTime =  ZonedDateTime.now(zoneId)

                                val date = currentTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) // e.g., 22 May 2025
                                val time = currentTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
                                duoViewModel.sendMessage(MessageData("",authViewModel.getAuthToken(),routeChatData.receiverId,textState.value,"",date,time))
                                textState.value = ""
                            },
                            duoViewModel,
                            onRecordingStart = {
                                voiceRecorderViewModel.startRecording(context)
                            },
                            onRecordingStop = {
                                val path = voiceRecorderViewModel.stopRecording()
                                Toast.makeText(context, "Saved: $path", Toast.LENGTH_SHORT).show()
                            }
                        )

                    }
                }
            }
        )
    }
}


@Composable
fun MessageLayout(messageData: MessageData,userId : String){

    if (!messageData.senderId.equals(userId)){
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart){
            MsgFrnd(messageData)
        }
    }else {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
            MsgYou(messageData)
        }
    }
}

@Composable
fun MessageInputField(
    text: String,
    onTextChange: (String) -> Unit,
    onSendMsg: ()-> Unit,
    duoViewModel: DuoViewModel,
    onRecordingStart: () -> Unit,
    onRecordingStop: () -> Unit
) {

    val textFieldType by duoViewModel.textFieldType.collectAsStateWithLifecycle()

    val replyToPersonName by duoViewModel.replyToName.collectAsStateWithLifecycle()

    val replyMsgContent by duoViewModel.replayContent.collectAsStateWithLifecycle()



    Row(Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp), verticalAlignment = Alignment.Bottom) {


        if (textFieldType){

            Card (Modifier.weight(1f), shape = RoundedCornerShape(15.dp)){

                Column (Modifier.fillMaxWidth()){

                    Box(Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                        )){
                        Box(Modifier
                            .fillMaxWidth()
                            .padding(7.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                                shape = RoundedCornerShape(10.dp)
                            )){

                            Box(Modifier
                                .fillMaxWidth()
                                .padding(7.dp), contentAlignment = Alignment.CenterEnd){
                                Icon(modifier = Modifier
                                    .size(15.dp)
                                    .clickable {
                                        duoViewModel.changeTextFieldType(false)
                                    }, painter = painterResource(R.drawable.ic_cancel), contentDescription = "ic_cancel")
                            }

                            Column (Modifier.padding(7.dp)){
                                Text("${replyToPersonName}",
                                    fontSize = 13.sp,
                                    fontFamily = zohoMedium,
                                    textAlign = TextAlign.Center,
                                )
                                Text("${replyMsgContent}",
                                    fontSize = 11.sp,
                                    fontFamily = zohoRegular,
                                    fontWeight = FontWeight.W500,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
//                    .border(0.5.dp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),RoundedCornerShape(50.dp))
                            .fillMaxWidth()
//                    .background(MaterialTheme.colorScheme.surfaceContainerLow.copy(alpha = 0.75f), RoundedCornerShape(50)) // Your dark rounded shape
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {

                        Row {
                            if (text.isEmpty()) {
                                Text(
                                    text = "Message",
                                    fontSize = 18.sp,
                                    fontFamily = zohoRegular,
                                    color = MaterialTheme.colorScheme.onSurface,
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
                                    .weight(1f)
                            )

                            Icon(painter = painterResource(R.drawable.baseline_attach_file_24), contentDescription = "Attach")
                        }

                    }
                }

            }

        }else {



            Column (Modifier.weight(1f)){
                Box(
                    modifier = Modifier
                        .border(
                            0.5.dp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                            RoundedCornerShape(50.dp)
                        )
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.surfaceContainerLow.copy(alpha = 0.75f),
                            RoundedCornerShape(50)
                        ) // Your dark rounded shape
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {

                    Row {
                        if (text.isEmpty()) {
                            Text(
                                text = "Message",
                                fontSize = 18.sp,
                                fontFamily = zohoRegular,
                                color = MaterialTheme.colorScheme.onSurface,
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
                                .weight(1f)
                        )

                        Icon(painter = painterResource(R.drawable.baseline_attach_file_24), contentDescription = "Attach")
                    }

                }
            }


        }

        Spacer(modifier = Modifier.width(5.dp))

        IconButton(
            modifier = Modifier.size(50.dp),
            onClick = {
                onSendMsg()
            },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )

        ) {
            Icon(
                painter = painterResource(if (text.isEmpty()) R.drawable.baseline_mic_24 else R.drawable.ic_send),
                contentDescription = "Send",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(26.dp)
            )
        }


    }
}


@Composable
fun MsgYou(messageData: MessageData) {
    Card (modifier = Modifier.padding(top = 5.dp, start = 30.dp), shape = RoundedCornerShape(topStart = 13.dp, bottomStart = 13.dp, bottomEnd = 13.dp), backgroundColor = MaterialTheme.colorScheme.primaryContainer){
        Column (modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.End){

            if (messageData.replyMsgId.isNotEmpty()) {
                Box(
                    Modifier
                        .padding(2.5.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.65f),
                            shape = RoundedCornerShape(5.dp)
                        )
                ) {
                    Column(Modifier.padding(5.dp)) {
                        Text(
                            "${messageData.replyToName}",
                            fontSize = 13.sp,
                            fontFamily = zohoMedium,
                        )
                        Text(
                            "${messageData.replyContent}",
                            fontSize = 11.sp,
                            fontFamily = zohoRegular,
                            fontWeight = FontWeight.W500,
                        )
                    }
                }
            }

            Text(
                "${messageData.msgContent}",
                fontSize = 17.sp,
                fontFamily = zohoRegular,
                fontWeight = FontWeight.W500,
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "${messageData.time}",
                    fontSize = 10.sp,
                    fontFamily = zohoLight,
                    fontWeight = FontWeight.W600,
                )

                Spacer(Modifier.width(2.dp))

                Icon(
                    modifier = Modifier.size(13.dp),
                    imageVector = Icons.Filled.DoneAll,
                    contentDescription = "ic_received"
                )
            }


        }
    }
}


@Composable
fun MsgFrnd(messageData: MessageData){
    Card (modifier = Modifier.padding(top = 5.dp, end = 30.dp), shape = RoundedCornerShape(topEnd = 13.dp, bottomStart = 13.dp, bottomEnd = 13.dp), backgroundColor = MaterialTheme.colorScheme.surface){
        Column (modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.Start){


            if (messageData.replyMsgId.isNotEmpty()) {
                Box(
                    Modifier
                        .padding(2.5.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.65f),
                            shape = RoundedCornerShape(5.dp)
                        )
                ) {

                    Column(Modifier.padding(5.dp)) {
                        Text(
                            "${messageData.replyToName}",
                            fontSize = 13.sp,
                            fontFamily = zohoMedium,
                        )
                        Text(
                            "${messageData.replyContent}",
                            fontSize = 11.sp,
                            fontFamily = zohoRegular,
                            fontWeight = FontWeight.W500,
                        )
                    }
                }
            }

            Text("${messageData.msgContent}",
                fontSize = 17.sp,
                fontFamily = zohoRegular,
                fontWeight = FontWeight.W500,
            )

            Text("${messageData.time}",
                fontSize = 10.sp,
                fontFamily = zohoLight,
                fontWeight = FontWeight.W600,
            )
        }
    }
}


@Preview(showBackground = false)
@Composable
fun MsgFrndPreview(){
    AppTheme {
//
//        val database = FirebaseRepository()
//        MessageInputField("", onTextChange = {}, onSendMsg = {}, duoViewModel = DuoViewModel(database))
//        MsgYou("Helo today fun","1:45 PM")
    }
}

//
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun ChatDarkScreen() {
//    AppTheme {
//        ChatScreen(rememberNavController(),"")
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ChatLightScreen() {
//    AppTheme {
//        ChatScreen(rememberNavController(),"")
//    }
//}