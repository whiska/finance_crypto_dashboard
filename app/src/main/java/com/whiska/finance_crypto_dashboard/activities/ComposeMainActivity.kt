package com.whiska.finance_crypto_dashboard.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whiska.finance_crypto_dashboard.R

class ComposeMainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        Scaffold(
            bottomBar = { BottomBar() },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /* TODO: Handle click */ },
                    backgroundColor = colorResource(id = R.color.purple)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.qrcode),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.Center
        ) {
            MainContent(it)
        }
    }

    @Composable
    fun MainContent(paddingValues: PaddingValues) {
        Column(modifier = Modifier.fillMaxSize()) {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(24.dp)
            ) {
                SearchBar()
                Spacer(modifier = Modifier.height(16.dp))
                SectionTitle(title = "Stock Futures")
                Spacer(modifier = Modifier.height(8.dp))
                // RecyclerView for stock_list_view can be implemented using LazyColumn
                Spacer(modifier = Modifier.height(16.dp))
                SectionTitle(title = "Sectors")
                Spacer(modifier = Modifier.height(8.dp))
                // Content for sectors
                Spacer(modifier = Modifier.height(16.dp))
                SectionTitle(title = "Crypto Activities")
                Spacer(modifier = Modifier.height(8.dp))
                // RecyclerView for crypto_list_view can be implemented using LazyColumn
            }
        }
    }

    @Composable
    fun SearchBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            BasicTextField(
                value = "",
                onValueChange = {},
                singleLine = true,
                textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                modifier = Modifier
                    .weight(1f)
                    .background(
                        painterResource(id = R.drawable.light_purple_bg),
                        shape = CircleShape
                    )
                    .padding(8.dp),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.search_icon),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box { innerTextField() }
                    }
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(painterResource(id = R.drawable.purple_bg), shape = CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

    @Composable
    fun SectionTitle(title: String) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6.copy(
                fontSize = 16.sp,
                color = colorResource(id = R.color.black)
            )
        )
    }

    @Composable
    fun BottomBar() {
        BottomAppBar(
            cutoutShape = CircleShape,
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomBarItem(icon = R.drawable.bottom_btn1)
                BottomBarItem(icon = R.drawable.bottom_btn2)
                Spacer(modifier = Modifier.width(32.dp)) // Space for FAB
                BottomBarItem(icon = R.drawable.bottom_btn3)
                BottomBarItem(icon = R.drawable.bottom_btn4)
            }
        }
    }

    @Composable
    fun BottomBarItem(icon: Int) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null
        )
    }

    @Composable
    fun ImageWithLabel(
        imageRes: Int,
        label: String,
        imageSize: Int = 64,
        labelSize: Int = 12
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .width(0.dp)
                .weight(0.25f)
        ) {
            Box(
                modifier = Modifier
                    .size(imageSize.dp)
                    .background(painterResource(id = R.drawable.oval_bg), shape = CircleShape)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                fontSize = labelSize.sp,
                color = colorResource(id = R.color.black),
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MainScreenPreview() {
        MainScreen()
    }

}