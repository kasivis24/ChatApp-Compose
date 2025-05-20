package com.mobile.chatapp.persentation.ui.screen.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.data.remote.db.Database
import com.mobile.chatapp.data.remote.state.DbEventState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val database: Database): ViewModel(){


    private val _uiProfileSetUpState = MutableStateFlow<DbEventState>(DbEventState.Idle)
    val uiProfileSetUpState : StateFlow<DbEventState> = _uiProfileSetUpState

    fun profileSetUp(profileData: ProfileData) {
        viewModelScope.launch (Dispatchers.IO){
            _uiProfileSetUpState.value = DbEventState.Loading
            _uiProfileSetUpState.value = database.addProfileData(profileData)
        }
    }


    fun reSetUiState(){
        _uiProfileSetUpState.value = DbEventState.Idle
    }


}