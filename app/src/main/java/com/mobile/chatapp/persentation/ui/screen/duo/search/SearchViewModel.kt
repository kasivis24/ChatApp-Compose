package com.mobile.chatapp.persentation.ui.screen.duo.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.data.remote.db.Database
import dagger.hilt.android.lifecycle.HiltViewModel
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


    fun searchProfile(searchQuery : String) {
        Log.d("LogData",searchQuery)
        viewModelScope.launch {
            _uiSearchState.value = SearchUiState.Loading
            _searchProfileList.value = database.searchProfile(searchQuery).value
            _uiSearchState.value = SearchUiState.Idle
        }
    }

}