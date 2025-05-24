package com.mobile.chatapp.di

import com.mobile.chatapp.data.remote.db.Database
import com.mobile.chatapp.data.remote.repo.FirebaseRepository
import com.mobile.chatapp.persentation.ui.screen.auth.repo.AuthRepository
import com.mobile.chatapp.persentation.ui.screen.auth.repo.GmailAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository = GmailAuthRepository()

    @Provides
    @Singleton
    fun provideFirebaseFireStoreRepository(): Database = FirebaseRepository()

}
