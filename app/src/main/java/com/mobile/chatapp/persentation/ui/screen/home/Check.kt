package com.mobile.chatapp.persentation.ui.screen.home
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.chatapp.persentation.ui.theme.AppTheme
import kotlinx.coroutines.launch
import kotlin.math.max

@Composable
fun Check(){
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        LongPressSlideButton()
    }
}

@Composable
fun LongPressSlideButton() {

    val scope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }

    val maxDragX = -100f
    val maxDragY = -100f

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .size(80.dp)
            .offset { IntOffset(offsetX.value.toInt(), offsetY.value.toInt()) }
            .background(Color.Red, CircleShape)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { offset ->
                        println("Slide Press started at $offset")
                        val success = tryAwaitRelease()
                        if (success) {
                            println("Slide Press released normally")
                        } else {
                            println("Slide Press canceled")
                        }
                    },
                    onLongPress = {
                        println("Slide Long press detected")
                    }
                )
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        val (dx, dy) = dragAmount
                        coroutineScope.launch {
                            if (dx < 0) {
                                offsetX.snapTo(max(offsetX.value + dx, maxDragX))
                            }
                            if (dy < 0) {
                                offsetY.snapTo(max(offsetY.value + dy, maxDragY))
                            }
                        }
                    },
                    onDragEnd = {
                        if (offsetX.value <= maxDragX) {
                            println("Slide left triggered")
                        }
                        if (offsetY.value <= maxDragY) {
                            println("Slide up triggered")
                        }
                        // Reset position
                        scope.launch {
                            offsetX.animateTo(0f)
                            offsetY.animateTo(0f)
                        }
                    }
                )
            }
    ) {
        Icon(
            imageVector = Icons.Default.Mic,
            contentDescription = "Mic",
            tint = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CheckPreview(){
    AppTheme {
        Check()
    }
}