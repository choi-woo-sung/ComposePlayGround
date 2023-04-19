package com.example.composeplayground.chapter4

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateTest() {
    var boxVisible by remember { mutableStateOf(true) }

    Column() {
        Row() {
            Button(onClick = { boxVisible = true }) {
                Text(text = "Show")
            }
            Button(onClick = { boxVisible = false }) {
                Text(text = "hide")
            }
        }
//        Crossfade(targetState = ) {
////
//        }

//        AnimatedVisibility(visible = boxVisible, enter = fadeIn(animationSpec = tween(durationMillis = 500, easing = Easing { }))) {
//            Row {
//                Box(Modifier.animateEnterExit()) {
//                }
//            }
//        }
    }
}

@Preview
@Composable
fun AnimateTestPreview() {
    AnimateTest()
}
