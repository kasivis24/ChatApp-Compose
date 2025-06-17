package com.mobile.chatapp.feature.audioplayer

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}