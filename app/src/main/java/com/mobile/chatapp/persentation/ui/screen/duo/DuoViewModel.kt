package com.mobile.chatapp.persentation.ui.screen.duo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.chatapp.data.dto.DuoFriendsData
import com.mobile.chatapp.data.dto.MessageData
import com.mobile.chatapp.data.remote.db.Database
import com.mobile.chatapp.data.remote.state.DbEventState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DuoViewModel @Inject constructor(private val database: Database): ViewModel(){

    private val _friendProfiles = MutableLiveData<List<DuoFriendsData>>(emptyList())
    val friendProfiles : LiveData<List<DuoFriendsData>> = _friendProfiles

    private val _messagesData = MutableLiveData<List<MessageData>>(emptyList())
    val messageData : LiveData<List<MessageData>> = _messagesData

    private val _isTextFieldType = MutableStateFlow(false)
    val textFieldType: StateFlow<Boolean> = _isTextFieldType

    private val _replyToName = MutableStateFlow("")
    val replyToName: StateFlow<String> = _replyToName

    private val _replayContent = MutableStateFlow("")
    val replayContent: StateFlow<String> = _replayContent

    private val _replyMsgId = MutableStateFlow("")
    val replyMsgId: StateFlow<String> = _replyMsgId

    private val _isUserUidState = MutableStateFlow<DbEventState>(DbEventState.Idle)
    val isUserUidState : StateFlow<DbEventState> = _isUserUidState

    fun getFriendProfiles(uId : String){
        viewModelScope.launch {
            database.getMyFriends(uId).observeForever { data ->
                _friendProfiles.value = data
            }
        }
    }

    fun changeTextFieldType(value: Boolean){
        _isTextFieldType.value = value
        Log.d("LogData","Check -> ${textFieldType.value}")
    }

    fun setReplyContents(name : String, msgContent : String, msgId : String){
        Log.d("LodData","name -> $name msgContent -> $msgContent")
        _replyToName.value = name
        _replayContent.value = msgContent
        _replyMsgId.value = msgId
    }

    fun sendMessage(messageData: MessageData) {

        Log.d("LogData","keyboard state -> ${textFieldType.value}")
        viewModelScope.launch {
            database.sendMessage(
                MessageData(
                messageData.msgId,
                messageData.senderId,
                messageData.receiverId,
                messageData.msgContent,
                messageData.contentType,
                messageData.date,
                messageData.time,
                if (textFieldType.value) replyMsgId.value else "",
                if (textFieldType.value) replyToName.value else "",
                if (textFieldType.value) replayContent.value else "",
                messageData.timeStamp
            ),
            )
        }
    }

    fun getDuoMessages(senderId : String,receiverId : String){
        viewModelScope.launch {
            database.getDuoMessage(senderId,receiverId).observeForever {
                _messagesData.value = it
            }
        }
    }

    fun putActiveStatus(uId: String){
        _isUserUidState.value = DbEventState.Loading
        viewModelScope.launch {
            _isUserUidState.value = database.putActiveStatus(uId)
        }
    }
}