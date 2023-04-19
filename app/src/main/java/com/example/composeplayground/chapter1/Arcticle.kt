package com.example.composeplayground.chapter1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeplayground.R
import com.example.composeplayground.ui.theme.ComposePlayGroundTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var click: Boolean by remember { mutableStateOf(true) }
            ComposePlayGroundTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Scaffold(
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = { Text(text = "기사") },
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.tertiary,
                                ),

                                actions = {
                                    IconButton(onClick = { /* doSomething() */ }) {
                                        Icon(
                                            imageVector = Icons.Filled.Favorite,
                                            contentDescription = "Localized description",
                                        )
                                    }
                                },
                                navigationIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = "Localized description",
                                    )
                                },
                            )
                        },

                    ) {
                        Surface(modifier = Modifier.padding(it)) {
                            Column() {
                                Spacer(modifier = Modifier.height(10.dp))
                                Header("배트맨 죽다") {
                                    Log.d("전파", "전파")
                                    click = !click
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Content(click)
                                Spacer(modifier = Modifier.height(10.dp))
                                LeftComment()
                                RightComment()
                            }
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Header(name: String, clickOnContinued: () -> Unit) {
        Surface(
            onClick = clickOnContinued,
            Modifier
                .border(
                    width = 4.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(20),
                )
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondary),

        ) {
            Text(
                text = name,
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 30.dp)
                    .wrapContentSize(Alignment.Center),
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 26.sp,
                fontFamily = FontFamily(Font(R.font.pretendardmedium)),
            )
        }
    }

    @Composable
    fun Content(click: Boolean) {
        Surface(
            Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessMedium,
                        visibilityThreshold = null,
                    ),
                )
                .height(if (click) 0.dp else 400.dp),
        ) {
            Row() {
//                Image(painter = painterResource(id = R.drawable.dog), contentDescription = null)
                Text(
                    text = "BTS의 갑작스런 활동 중단은 오늘 전세계 언론사의 주요 뉴스였습니다.\n" +
                        "\n" +
                        "CNN은 도쿄에서.\n" +
                        "\n",
                )
            }
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
        Surface(
            Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.CenterEnd),
        ) {
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
                painter = painterResource(id = R.drawable.wooman),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    }

    @Composable
    fun Chat(text: String) {
        Column() {
            Text(text = "니콜라", style = MaterialTheme.typography.labelMedium)
            Surface(
                shadowElevation = 2.dp,
                shape = RoundedCornerShape(10),
                modifier = Modifier.padding(4.dp),
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(4.dp),
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ComposePlayGroundTheme {
            Header("Android", clickOnContinued = {})
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun CommentPreview() {
        ComposePlayGroundTheme {
            LeftComment()
        }
    }
}
