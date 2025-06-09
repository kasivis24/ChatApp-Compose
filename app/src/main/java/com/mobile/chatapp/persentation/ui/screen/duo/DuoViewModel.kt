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

    fun sendMessage(messageData: MessageData) {
        viewModelScope.launch {
            database.sendMessage(messageData)
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