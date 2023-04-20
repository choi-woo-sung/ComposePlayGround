package com.example.composeplayground.holocard

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeplayground.R
import kotlin.math.abs

@Composable
fun HoloCard() {
    val context = LocalContext.current
    val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    var sensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)

    var orientationState by remember { mutableStateOf(floatArrayOf(0f, 0f, 0f)) }

    val test = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            val rotationMatrix = FloatArray(9)
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event?.values)
            val orientationValues = FloatArray(3)
            SensorManager.getOrientation(rotationMatrix, orientationValues)
            val orientation =
                orientationValues.map { Math.toDegrees(it.toDouble()).toFloat() }.toFloatArray()
            orientationState = orientation
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        }
    }
    DisposableEffect(Unit) {
        sensorManager.registerListener(test, sensor, SensorManager.SENSOR_DELAY_NORMAL)

        onDispose {
            sensorManager.unregisterListener(test)
        }
    }
    var clickState by remember { mutableStateOf(false) }

    val rotationX: Float by animateFloatAsState(
        if (clickState) 1f else 180f,
        tween(
            durationMillis = 2000,
            delayMillis = 0,
            easing = FastOutLinearInEasing,
        ),
    )

    fun isHorizontallyFlip() = (abs(rotationX) % 360 > 90f && abs(rotationX) % 360 < 270f)

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        if (isHorizontallyFlip()) {
            Box(
                Modifier
                    .clickable { clickState = !clickState }
                    .align(Alignment.Center)
                    .width(300.dp)
                    .height(600.dp)
                    .graphicsLayer(
                        rotationY = rotationX,
                    ),
            ) {
                HoloBack()
            }
        } else {
            Box(
                Modifier
                    .align(Alignment.Center)
                    .width(300.dp)
                    .height(600.dp)
                    .graphicsLayer(
                        rotationX = orientationState[1] / 3,
                        rotationY = rotationX + orientationState[2] / 3,
                    ).drawWithContent {
                        drawContent()
                        val colors = listOf(Color.Black, Color(0xFFB6FBFF))
                        val background = Brush.linearGradient(
                            colors = colors,
                            start = Offset(0f, 0f),

                            end = Offset(orientationState[1] / 3f, orientationState[2] / 3f),
                        )

                        drawRect(brush = background, blendMode = BlendMode.ColorDodge)
                    },
            ) {
                HoloFront()
            }
        }
    }
// 센서 api로 움직여볼꺼야

// 빛이나는 색깔을 넣어볼꺼
}

@Composable
fun HoloFront() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(MaterialTheme.shapes.medium)
            .background(color = Color.White),
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.woosung),
            contentDescription = "Ellipse 1",
            modifier = Modifier
                .size(200.dp)
                .clip(shape = CircleShape)
                .border(
                    3.dp,
                    shape = CircleShape,
                    color = Color(0xff5a8107),

                )
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop,

        )
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "잠깐! CONTENT DESCRIPTION이 NULL이라규??",
            color = Color.Black,
            style = TextStyle(
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "최우성",
            color = Color(0xff5a8107),
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(MaterialTheme.shapes.medium)
                .background(color = Color(0xffffe1e2)),
        ) {
            Text(
                text = "혹시 content description에 null이나 빈값을 넣고 계시진 않으신가요?혹시나 텍스트가 담긴 컴포넌트를 고정값으로 주고있으시진 않으신가요?이번주제에서는 접근성에는 어떤것이있고, 왜 필요한지와어떻게 대응하는지에 대해 소개하고자 합니다.",
                color = Color(0xff181a1d),
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 21.sp),
                modifier = Modifier
                    .width(width = 482.dp)
                    .height(height = 179.dp),
            )
        }
    }
}

@Composable
fun HoloBack() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(MaterialTheme.shapes.medium)
            .background(Color(red = 1, 14, 54)),
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.chazzel),
            contentDescription = "찰스다뇨",
        )
    }
}

@Preview
@Composable
fun HoloCardPreview() {
    HoloCard()
}

@Preview
@Composable
fun HoloBackPreview() {
    HoloBack()
}

@Preview
@Composable
fun FramePreview() {
    HoloFront()
}
