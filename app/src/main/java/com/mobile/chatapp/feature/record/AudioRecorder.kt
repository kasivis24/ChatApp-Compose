package com.mobile.chatapp.feature.record
import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}