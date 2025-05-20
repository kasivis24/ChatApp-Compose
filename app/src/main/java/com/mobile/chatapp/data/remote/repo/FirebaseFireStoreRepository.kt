package com.mobile.chatapp.data.remote.repo

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.data.dto.UserData
import com.mobile.chatapp.data.remote.db.Database
import com.mobile.chatapp.data.remote.state.DbEventState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class FirebaseFireStoreRepository : Database {

    private val firebaseFireStore = FirebaseFirestore.getInstance()


    override suspend fun addUser(userData: UserData) : DbEventState {

        return try {
            firebaseFireStore.collection("userData").document()
                .set(userData).await()
            DbEventState.Success("Userdata Added Success")
        }
        catch (e : Exception){
            DbEventState.Error("Something went wrong")
        }
    }

    override suspend fun addProfileData(profileData: ProfileData): DbEventState {
        return try {
            firebaseFireStore.collection("profileData").document()
                .set(profileData).await()
            DbEventState.Success("ProfileData Added Success")
        }
        catch (e : Exception){
            DbEventState.Error("Something went wrong")
        }
    }

    override suspend fun searchProfile(searchQuery: String): StateFlow<List<ProfileData>> {
        val _profilesFlow = MutableStateFlow<List<ProfileData>>(emptyList())

        try {
            val snapshot = FirebaseFirestore.getInstance()
                .collection("profileData")
                .get()
                .await()

            val profiles = snapshot.documents.mapNotNull { it.toObject(ProfileData::class.java) }
                .filter { profile ->
                    profile.name.contains(searchQuery, ignoreCase = true) || profile.mail.contains(searchQuery, ignoreCase = true)
                }

            _profilesFlow.value = profiles
            Log.d("LogData","Result -> ${_profilesFlow.value}")
        } catch (e: Exception) {
            e.printStackTrace()
            _profilesFlow.value = emptyList()
            Log.d("LogData","Failed -> $e")
        }

        return _profilesFlow.asStateFlow()
    }


}