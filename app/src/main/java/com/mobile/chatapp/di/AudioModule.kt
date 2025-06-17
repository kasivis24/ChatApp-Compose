package com.mobile.chatapp.di
import android.content.Context
import com.mobile.chatapp.feature.audioplayer.AndroidAudioPlayer
import com.mobile.chatapp.feature.audioplayer.AudioPlayer
import com.mobile.chatapp.feature.record.AndroidAudioRecorder
import com.mobile.chatapp.feature.record.AudioRecorder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AudioModule {

    @Provides
    @Singleton
    fun provideAudioRecorder(
        @ApplicationContext context: Context
    ): AudioRecorder = AndroidAudioRecorder(context)

    @Provides
    @Singleton
    fun provideAudioPlayer(
        @ApplicationContext context: Context
    ): AudioPlayer = AndroidAudioPlayer(context)

    @Provides
    fun provideAudioFile(
        @ApplicationContext context: Context
    ): File = File(context.cacheDir, "audio.mp3")
}
