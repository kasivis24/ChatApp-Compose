package com.mobile.chatapp.persentation.ui.screen.duo.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.data.remote.db.Database
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(private val database: Database): ViewModel(){
    private val _profileData = MutableLiveData<ProfileData>()
    val profileData: MutableLiveData<ProfileData> = _profileData

    fun getProfileData(uId : String){
        viewModelScope.launch {
            database.getProfileData(uId).observeForever {
                _profileData.value = it
            }
        }
    }
}