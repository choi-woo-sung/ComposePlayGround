package com.example.composeplayground.chapter4

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.sin

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CanvasTest() {
    Canvas(modifier = Modifier.size(300.dp)) {
        val width = size.width
        val height = size.height
        val points = mutableListOf<Offset>()

        for (x in 0..size.width.toInt()) {
            val y = (sin(x * (2f * PI / width)) * (height / 2) + (height / 2)).toFloat()
            points.add(Offset(x.toFloat(), y))
        }
        drawPoints(
            points = points,
            strokeWidth = 3f,
            pointMode = PointMode.Points,
            color = Color.Blue
        )
    }
}

@Preview
@Composable
fun CanvasTestPreview() {
    CanvasTest()
}
