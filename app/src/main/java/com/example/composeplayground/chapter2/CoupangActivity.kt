package com.example.composeplayground.chapter2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composeplayground.R
import com.example.composeplayground.chapter1.ui.theme.ComposePlayGroundTheme
import com.google.accompanist.pager.*

class CoupangActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var introState by rememberSaveable {
                mutableStateOf(true)
            }
            ComposePlayGroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (introState) {
                        Intro(onIntroValueChange = { int ->
                            introState = int
                        })
                    } else Greeting()
                }
            }
        }
    }
}

@Composable
// 함수를 만들려면 파라미터와 +리턴 타입이 필요 , void(Unit)는 리턴타입이 없음
// 파라미터는 함수를 생성할때, 필요한 변수

private fun Intro(onIntroValueChange: (int: Boolean) -> Unit) {
    Button(onClick = {
        onIntroValueChange(false)
    }) {
//
    }
}

//

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun Greeting() {
    Scaffold(
        topBar = {
            Column() {
                CenterAlignedTopAppBar(title = {
                    Image(
                        painter = painterResource(id = R.drawable.coupang_logo),
                        contentDescription = ""
                    )
                }, actions = {
                        IconButton(
                            onClick = { /*TODO*/ }

                        ) {
                            Icon(imageVector = Icons.Filled.Notifications, contentDescription = "")
                        }
                    })
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().height(40.dp).padding(
                        horizontal = 16.dp
                    ),
                    value = "",
                    onValueChange = {}

                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        },
        bottomBar = {
            NavigationBar(modifier = Modifier.height(70.dp)) {
                NavigationBarItem(
                    onClick = { /*TODO*/ },
                    selected = false,
                    icon = {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
                    },
                    label = { Text(text = "카테고리") },
                    alwaysShowLabel = true
                )
                NavigationBarItem(
                    onClick = { /*TODO*/ },
                    selected = false,
                    icon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                    },
                    label = { Text(text = "검색") },
                    alwaysShowLabel = true
                )
                NavigationBarItem(
                    onClick = { /*TODO*/ },
                    selected = false,
                    icon = {
                        Icon(imageVector = Icons.Filled.Home, contentDescription = "")
                    },
                    label = { Text(text = "홈") },
                    alwaysShowLabel = true
                )
                NavigationBarItem(
                    onClick = { /*TODO*/ },
                    selected = false,
                    icon = {
                        Icon(imageVector = Icons.Filled.Male, contentDescription = "")
                    },
                    label = { Text(text = "마이쿠팡") },
                    alwaysShowLabel = true
                )
                NavigationBarItem(
                    onClick = { /*TODO*/ },
                    selected = false,
                    icon = {
                        Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "")
                    },
                    label = { Text(text = "장바구니") },
                    alwaysShowLabel = true
                )
            }
        }

    ) {
        Surface(modifier = Modifier.padding(it)) {
            Column() {
                AdverTisePager()
                CategoryMenu()
                Goguma()
            }
        }
    }
}

@Composable
fun Goguma() {
}

@Composable
fun CategoryMenu() {
    val lazyGridState = rememberLazyGridState()
    val contentPadding = PaddingValues(16.dp)
    Box() {
        LazyHorizontalGrid(
            state = lazyGridState,
            flingBehavior = ScrollableDefaults.flingBehavior(),
            rows = GridCells.Fixed(2),
            modifier = Modifier.height(200.dp)
        ) {
            items(count = 30) {
                AsyncImage(
                    modifier = Modifier,
                    model = ImageRequest.Builder(LocalContext.current).data(ImageItem(seed = it))
                        .crossfade(true).build(),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AdverTisePager() {
    var pageState = rememberPagerState(0)

    Box() {
        HorizontalPager(
            count = 5,
            state = pageState
        ) { page ->
            AsyncImage(
                modifier = Modifier.fillMaxWidth().height(200.dp),

                model = ImageRequest.Builder(LocalContext.current).data(ImageItem(seed = page))
                    .crossfade(true).build(),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
        }
        HorizontalPagerIndicator(
            pagerState = pageState,
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 10.dp),
            activeColor = Color.White,
            inactiveColor = Color.Black
        )
    }
}

@Composable
fun ImageItem(
    seed: Int,
    width: Int = 300,
    height: Int = width
) = "https://picsum.photos/seed/$seed/$width/$height"

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePlayGroundTheme {
        Greeting()
    }
}
