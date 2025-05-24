package com.mobile.chatapp.persentation.ui.screen.settings.viewmodel

import android.net.Uri
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


    fun uploadProfile(uri: Uri,uId : String,mail : String,name : String,bio : String) {


        _uiProfileSetUpState.value = DbEventState.Loading
        if (mail.isEmpty() || name.isEmpty() || bio.isEmpty() || uri.toString().isEmpty()){
            _uiProfileSetUpState.value = DbEventState.Error("Something went wrong")
        }else{
            viewModelScope.launch (Dispatchers.IO){
                database.uploadProfileImage(
                    uri,
                    onSuccessImage = {imageUrl->
                        viewModelScope.launch (Dispatchers.IO){
                            _uiProfileSetUpState.value = database.addProfileData(ProfileData(uId,imageUrl,name,mail,bio))
                        }
                    }
                )
            }
        }

    }


    fun reSetUiState(){
        _uiProfileSetUpState.value = DbEventState.Idle
    }


}