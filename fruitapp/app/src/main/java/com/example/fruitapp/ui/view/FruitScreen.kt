package com.example.fruitapp.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fruitapp.R
import com.example.fruitapp.data.model.Fruit
import com.example.fruitapp.ui.viewmodel.FruitViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FruitScreen(navController: NavController, viewModel: FruitViewModel) {
    val fruits by viewModel.more_fruit.observeAsState(initial = emptyList())
    val pagerState = rememberPagerState(pageCount = { 3})

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Fructus",
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black.copy(0.9f))
            )
        }
    ) { PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()

                .padding(PaddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (fruits.isNotEmpty()) {
                HorizontalPager(state = pagerState) { page ->
                    PageContent(fruit = fruits[page], navController, pagerState)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageContent(fruit: Fruit, navController: NavController, pagerState: PagerState) {
    Column(
        modifier = Modifier.fillMaxSize().background(fruit.color),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = fruit.imageResId),
            contentDescription = fruit.name,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(350.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = fruit.name,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = fruit.description,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { navController.navigate("fruits_list") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCE93D8)),
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .padding(top = 16.dp)
        ) {
            Text(
                "Start",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }



        Row(
            modifier = Modifier.padding(top = 16.dp, bottom = 15.dp).fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            repeat(3) { index ->
                Box(
                    modifier = Modifier
                        .size(if (pagerState.currentPage == index) 12.dp else 8.dp)
                        .background(
                            if (pagerState.currentPage == index) Color.White else Color.Gray,
                            RoundedCornerShape(50)
                        )
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}
