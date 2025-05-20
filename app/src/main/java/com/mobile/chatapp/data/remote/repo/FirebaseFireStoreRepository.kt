package com.mobile.chatapp.data.remote.repo

import com.google.firebase.firestore.FirebaseFirestore
import com.mobile.chatapp.data.dto.ProfileData
import com.mobile.chatapp.data.dto.UserData
import com.mobile.chatapp.data.remote.db.Database
import com.mobile.chatapp.data.remote.state.DbEventState
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


}