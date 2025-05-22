package com.mobile.chatapp.persentation.ui.screen.duo.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.data.dto.RequestData
import com.mobile.chatapp.data.remote.db.Database
import com.mobile.chatapp.data.remote.state.DbEventState
import com.mobile.chatapp.persentation.ui.screen.auth.viewmodel.AuthViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val database: Database): ViewModel() {

    private val _searchProfileList = MutableStateFlow<List<ProfileData>>(emptyList())
    val searchProfileList : StateFlow<List<ProfileData>> = _searchProfileList


    private val _uiSearchState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiSearchState : StateFlow<SearchUiState> = _uiSearchState

    private val _uiSendRequestState = MutableStateFlow<DbEventState>(DbEventState.Idle)
    val uiSendRequestState : StateFlow<DbEventState> = _uiSendRequestState


    fun searchProfile(searchQuery : String,uId : String) {
        Log.d("LogData",searchQuery)
        viewModelScope.launch {
            _uiSearchState.value = SearchUiState.Loading
            _searchProfileList.value = database.searchProfile(searchQuery,uId).value
            _uiSearchState.value = SearchUiState.Idle
        }
    }


    fun sendFollowRequest(senderId : String, receiverId : String,day : String,date : String,time :String){
        viewModelScope.launch (Dispatchers.IO){
            _uiSendRequestState.value = DbEventState.Loading
            _uiSendRequestState.value = database.sendRequest(senderId,receiverId,day,date,time)
        }
    }

}