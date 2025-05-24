package com.mobile.chatapp.persentation.ui.screen.duo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.chatapp.data.dto.DuoFriendsData
import com.mobile.chatapp.data.remote.db.Database
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DuoViewModel @Inject constructor(private val database: Database): ViewModel(){

    private val _friendProfiles = MutableLiveData<List<DuoFriendsData>>(emptyList())
    val friendProfiles : LiveData<List<DuoFriendsData>> = _friendProfiles

    fun getFriendProfiles(uId : String){
        viewModelScope.launch {
            database.getMyFriends(uId).observeForever { data ->
                _friendProfiles.value = data
            }
        }
    }
}