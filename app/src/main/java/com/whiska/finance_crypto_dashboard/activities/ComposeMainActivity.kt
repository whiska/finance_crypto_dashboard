package com.whiska.finance_crypto_dashboard.activities

import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.majorik.sparklinelibrary.SparkLineLayout

import com.whiska.finance_crypto_dashboard.R
import com.whiska.finance_crypto_dashboard.provideCryptoList
import com.whiska.finance_crypto_dashboard.provideStockList

class ComposeMainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (scrollView, bottomAppBar, fab) = createRefs()

            Column(
                modifier = Modifier
                    .constrainAs(scrollView) {
                        top.linkTo(parent.top)
                        bottom.linkTo(bottomAppBar.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    }
                    .verticalScroll(rememberScrollState())
            ) {
                SearchBar()
                StockFutures()
                StockList()
                SectorSection()
                ActionButtons()
                CryptoActivities()
                CryptoList()
            }

            BottomAppBar(
                modifier = Modifier.constrainAs(bottomAppBar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            FloatingActionButton(
                onClick = { /* Handle click */ },
                modifier = Modifier
                    .constrainAs(fab) {
                        bottom.linkTo(bottomAppBar.top, margin = 16.dp)
                        end.linkTo(bottomAppBar.end, margin = 16.dp)
                    }
                    .background(Color(0xFF6200EE))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.qrcode),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }

    @Composable
    fun SearchBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                placeholder = { Text(text = "Search") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = null
                    )
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            ConstraintLayout(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color(0xFF6200EE))
            ) {
                val (image) = createRefs()
                Image(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = null,
                    modifier = Modifier.constrainAs(image) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MainScreenPreview() {
        MainScreen()
    }

    @Composable
    fun StockFutures() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Stock Futures",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "See All",
                color = Color(0xFF6200EE)
            )
        }
    }

    @Composable
    fun StockList() {
        val stockList = remember { provideStockList() }
        LazyRow {
            items(stockList) {
                StockCard(
                    stockImage = it.resourceId,
                    stockName = it.name,
                    stockPrice = it.price,
                    stockChangePercent = it.changePercent,
                    sparkLineColor = Color(resources.getColor(R.color.spark_line_color, theme)),
                    sparkLineThickness = Dp(2f),
                    markerBackgroundColor = Color(resources.getColor(R.color.spark_line_color, theme)),
                    markerWidth = Dp(8f),
                    lineData = it.lineData
                )
            }
        }
    }

    @Composable
    fun SectorSection() {
        Text(
            text = "Sectors",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 24.dp)
        )
    }

    @Composable
    fun ActionButtons() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            val modifier = Modifier.weight(1f)
            ActionButton(iconRes = R.drawable.btn_1, label = "Deposit", modifier)
            ActionButton(iconRes = R.drawable.btn_2, label = "Withdraw", modifier)
            ActionButton(iconRes = R.drawable.btn_3, label = "Add", modifier)
            ActionButton(iconRes = R.drawable.btn_4, label = "View All", modifier)
        }
    }

    @Composable
    fun ActionButton(iconRes: Int, label: String, modifier: Modifier) {
        Column(
            modifier = modifier,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color(0xFFE0E0E0))
            ) {
                val (image) = createRefs()
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.constrainAs(image) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                )
            }
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

    @Composable
    fun CryptoActivities() {
        Text(
            text = "Crypto Activities",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 24.dp)
        )
    }

    @Composable
    fun CryptoList() {
        val cryptoList = remember {provideCryptoList()}
        LazyColumn {
           items(cryptoList) {
               CryptoCard(
                   cryptoImage = it.resourceId,
                   cryptoName = it.name,
                   cryptoPrice = it.price,
                   cryptoChangePercent = it.changePercent,
                   cryptoSize = it.propertySize,
                   cryptoValue = it.propertyAmount,
                   sparkLineColor = Color(resources.getColor(R.color.spark_line_color, theme)),
                   sparkLineThickness = Dp(2f),
                   markerBackgroundColor = Color(resources.getColor(R.color.spark_line_color, theme)),
                   markerWidth = Dp(4f),
                   lineData = it.lineData
               )
           }
        }
    }

    @Composable
    fun BottomAppBar(modifier: Modifier) {
        androidx.compose.material3.BottomAppBar(
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BottomAppBarButton(R.drawable.bottom_btn1)
                BottomAppBarButton(R.drawable.bottom_btn2)
                Spacer(modifier = Modifier.width(56.dp)) // Space for the FAB
                BottomAppBarButton(R.drawable.bottom_btn3)
                BottomAppBarButton(R.drawable.bottom_btn4)
            }
        }
    }

    @Composable
    fun BottomAppBarButton(iconRes: Int) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }

    @Composable
    fun StockCard(
        stockImage: Int,
        stockName: String,
        stockPrice: Double,
        stockChangePercent: Double,
        sparkLineColor: Color,
        sparkLineThickness: Dp,
        markerBackgroundColor: Color,
        markerWidth: Dp,
        lineData: ArrayList<Int>
    ) {
        ConstraintLayout(
            modifier = Modifier
                .width(210.dp)
                .height(140.dp)
                .padding(16.dp)
        ) {
            val (image, name, price, changePercent, sparkLine) = createRefs()

            Image(
                painter = painterResource(stockImage),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )

            Text(
                text = stockName,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .constrainAs(name) {
                        top.linkTo(image.top)
                        start.linkTo(image.end)
                        bottom.linkTo(image.bottom)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = stockPrice.toString(),
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier.constrainAs(price) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    bottom.linkTo(changePercent.top)
                }
            )

            Text(
                text = stockChangePercent.toString(),
                color = Color(0xFF12C737),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(changePercent) {
                    top.linkTo(price.bottom)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
            )

            DrawSparkLine(
                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp)
                    .constrainAs(sparkLine) {
                        top.linkTo(image.bottom)
                        start.linkTo(price.end)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                lineColor = sparkLineColor,
                lineThickness = sparkLineThickness,
                markerBackgroundColor = markerBackgroundColor,
                markerWidth = markerWidth,
                lineData = lineData
            )
        }
    }

    @Composable
    fun CryptoCard(
        cryptoImage: Int,
        cryptoName: String,
        cryptoPrice: Double,
        cryptoChangePercent: Double,
        cryptoSize: Double,
        cryptoValue: Double,
        sparkLineColor: Color,
        sparkLineThickness: Dp,
        markerBackgroundColor: Color,
        markerWidth: Dp,
        lineData: ArrayList<Int>) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
                .background(color = Color.LightGray) // Replace with your background resource
        ) {
            val (cryptoImg, cryptoNameLbl, cryptoPriceLbl, cryptoChangePercentLbl, propertySizeLbl, propertyValueLbl, sparkLine) = createRefs()

            Image(
                painter = painterResource(id = cryptoImage),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .constrainAs(cryptoImg) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                contentScale = ContentScale.Crop
            )

            Text(
                text = cryptoName,
                style = androidx.compose.ui.text.TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                ),
                modifier = Modifier.constrainAs(cryptoNameLbl) {
                    start.linkTo(cryptoImg.end, margin = 8.dp)
                    top.linkTo(cryptoImg.top)
                }
            )

            Text(
                text = cryptoPrice.toString(),
                style = androidx.compose.ui.text.TextStyle(
                    color = Color.Black,
                    fontSize = 12.sp
                ),
                modifier = Modifier.constrainAs(cryptoPriceLbl) {
                    start.linkTo(cryptoImg.end, margin = 8.dp)
                    bottom.linkTo(cryptoImg.bottom)
                }
            )

            Text(
                text = cryptoChangePercent.toString(),
                style = androidx.compose.ui.text.TextStyle(
                    color = Color(0xFF12C737),
                    fontSize = 12.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                ),
                modifier = Modifier.constrainAs(cryptoChangePercentLbl) {
                    start.linkTo(cryptoPriceLbl.end, margin = 8.dp)
                    bottom.linkTo(cryptoPriceLbl.bottom)
                    top.linkTo(cryptoPriceLbl.top)
                }
            )

            Text(
                text = cryptoSize.toString(),
                style = androidx.compose.ui.text.TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                ),
                modifier = Modifier.constrainAs(propertySizeLbl) {
                    end.linkTo(parent.end)
                    top.linkTo(cryptoNameLbl.top)
                }
            )

            Text(
                text = cryptoValue.toString(),
                style = androidx.compose.ui.text.TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp
                ),
                modifier = Modifier.constrainAs(propertyValueLbl) {
                    end.linkTo(parent.end)
                    bottom.linkTo(cryptoChangePercentLbl.bottom)
                }
            )

            DrawSparkLine(
                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp)
                    .constrainAs(sparkLine) {
                        top.linkTo(cryptoImg.bottom)
                        start.linkTo(cryptoPriceLbl.end)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                lineColor = sparkLineColor,
                lineThickness = sparkLineThickness,
                markerBackgroundColor = markerBackgroundColor,
                markerWidth = markerWidth,
                lineData = lineData

            )
        }
    }

    @Composable
    fun DrawSparkLine(
        modifier: Modifier = Modifier,
        lineColor: Color,
        lineThickness: Dp,
        markerBackgroundColor: Color,
        markerWidth: Dp,
        lineData: ArrayList<Int>
    ) {
        // Assuming you have a custom implementation for SparkLineLayout in Compose
        AndroidView(
            factory = { context ->
                SparkLineLayout(context).apply {
                    this.sparkLineColor = lineColor.toArgb()
                    this.sparkLineThickness = lineThickness.value
                    this.markerBackgroundColor = markerBackgroundColor.toArgb()
                    this.markerWidth = markerWidth.value
                    this.setData(lineData)
                }
            },
            modifier = modifier
        )
    }
}