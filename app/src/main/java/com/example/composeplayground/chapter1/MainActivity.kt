package com.example.composeplayground.chapter1

import android.hardware.lights.Light
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeplayground.R
import com.example.composeplayground.ui.theme.ComposePlayGroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlayGroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column() {
                        Header("배트맨 죽다")
                        Spacer(modifier = Modifier.height(10.dp))
                        Content()
                        Spacer(modifier = Modifier.height(10.dp))
                        LeftComment()
                        RightComment()
                    }
                }
            }
        }
    }
}

@Composable
fun Header(name: String) {
    Surface(
        Modifier
            .border(
                width = 4.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(20)
            )
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.secondary)
    ) {
        Text(
            text = name,
            modifier = Modifier
                .padding(top = 30.dp, bottom = 30.dp)
                .wrapContentSize(Alignment.Center),
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 26.sp
        )
    }
}

@Composable
fun Content() {
    Row() {
        Image(painter = painterResource(id = R.drawable.dog), contentDescription = null)
        Text(
            text = "BTS의 갑작스런 활동 중단은 오늘 전세계 언론사의 주요 뉴스였습니다.\n" +
                    "\n" +
                    "CNN은 도쿄에서.\n" +
                    "\n"
        )
    }
}

@Composable
fun LeftComment() {
    Row() {
        Face()
        Spacer(modifier = Modifier.width(4.dp))
        Chat("아니 실화야?")
    }
}

@Composable
fun RightComment() {
    Surface(Modifier.fillMaxWidth().wrapContentSize(Alignment.CenterEnd)) {
        Row() {
            Chat("아 bts오빠들 봐야하는데... ")
            Spacer(modifier = Modifier.width(4.dp))
            Face()
        }
    }
}

@Composable
fun Face() {
    Surface(
        shape = CircleShape,
        modifier = Modifier
            .size(50.dp)
            .border(2.dp, color = MaterialTheme.colorScheme.secondary, shape = CircleShape),
    ) {
        Image(
            painter = painterResource(id = R.drawable.wooman), contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun Chat(text : String) {
    Column() {
        Text(text = "니콜라", style = MaterialTheme.typography.labelMedium)
        Surface(shadowElevation = 2.dp, shape = RoundedCornerShape(10) , modifier = Modifier.padding(4.dp)) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePlayGroundTheme {
        Header("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun CommentPreview() {
    ComposePlayGroundTheme {
        LeftComment()
    }
}