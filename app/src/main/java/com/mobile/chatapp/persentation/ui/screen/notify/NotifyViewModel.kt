package com.mobile.chatapp.persentation.ui.screen.notify

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.chatapp.data.dto.DuoRequestData
import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.data.remote.db.Database
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotifyViewModel @Inject constructor(private val database: Database): ViewModel(){

    private val _requestProfiles = MutableLiveData<List<DuoRequestData>>(emptyList())
    val requestProfiles : LiveData<List<DuoRequestData>> = _requestProfiles


    fun getRequestProfiles(uId : String){
        viewModelScope.launch {
            database.getRequests(uId).observeForever {data->
                _requestProfiles.value = data
            }
        }
    }


    fun declineRequest(requestId : String){
        viewModelScope.launch (Dispatchers.IO){
            database.declineRequest(requestId)
        }
    }


}