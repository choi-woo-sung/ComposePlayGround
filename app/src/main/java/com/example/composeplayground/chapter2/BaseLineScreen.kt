package com.example.composeplayground.chapter2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeplayground.chapter1.ui.theme.ComposePlayGroundTheme

class BaseLineScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlayGroundTheme {
                InstagramText()
            }
        }
    }
}

@Composable
fun InstagramText() {
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    BasicTextField(
        "호호호 나는 천재다",
        onValueChange = {},
        onTextLayout = {
            textLayoutResult = it
//            onTextLayout(it)
        },
        decorationBox = { innerTextField ->
            RoundedBackground(
                textLayoutResult = textLayoutResult,
                backgroundParams = BackgroundParams(10.dp),
                textSize = 12.sp
            )
            innerTextField
        }

    )
}

@Preview
@Composable
fun InstagramPreview() {
    ComposePlayGroundTheme {
        Surface(Modifier.background(Color.White)) {
            InstagramText()
        }
    }
}
