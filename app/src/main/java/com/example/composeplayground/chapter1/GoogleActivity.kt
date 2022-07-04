package com.example.composeplayground.chapter1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplayground.R
import com.example.composeplayground.chapter1.ui.theme.ComposePlayGroundTheme

class GoogleActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlayGroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Content()
                }
            }
        }
    }

    @Composable
    fun TobBar() {
        var tabIndex by remember { mutableStateOf(0) }
        val tabTitle = listOf("ALL", "IMAGES")

        TabRow(selectedTabIndex = tabIndex) {
            tabTitle.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex == index, onClick = { tabIndex = index },
                    modifier = Modifier.wrapContentSize(),
                    text = { Text(text = title) }
                )
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(showBackground = true)
    @Composable
    fun Content() {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = {
                        TobBar()
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.Dashboard,
                                contentDescription = "대시 보드"
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.ImageSearch,
                                contentDescription = "대시 보드"
                            )
                        }
                    }
                )
            }
        ) {
            Surface(modifier = Modifier.padding(it)) {
                Column() {
                    Image(
                        painter = painterResource(id = R.drawable.googlelogo_color_160x56dp),
                        modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.Center)
                            .padding(
                                vertical = 40.dp
                            ),
                        contentDescription = "",
                    )
                    var text by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = text, onValueChange = { text = it },
                        shape = CircleShape,
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(Icons.Filled.Search, null)
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                            }
                        )
                    )
                }
            }
        }
    }
}
