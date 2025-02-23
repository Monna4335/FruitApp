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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fruitapp.R
import com.example.fruitapp.data.model.Fruit
import com.example.fruitapp.ui.view.viewmodel.FruitViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FruitScreen(navController: NavController, viewModel: FruitViewModel) {
    val fruits = viewModel.fruits
    val pagerState = rememberPagerState(pageCount = { 3 })

    Scaffold(
        topBar = { FruitTopAppBar() }
    ) { paddingValues ->
        FruitContent(paddingValues, fruits, pagerState, navController,viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitTopAppBar() {
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FruitContent(
    paddingValues: PaddingValues,
    fruits: List<Fruit>,
    pagerState: PagerState,
    navController: NavController,
    viewModel: FruitViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (fruits.isNotEmpty()) {
            HorizontalPager(state = pagerState) { page ->
                PageContent(fruit = fruits[page], navController, pagerState,viewModel)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageContent(fruit: Fruit, navController: NavController, pagerState: PagerState,viewModel: FruitViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fruit.color),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        FruitImage(fruit)
        Spacer(modifier = Modifier.height(40.dp))
        FruitName(fruit)
        Spacer(modifier = Modifier.height(20.dp))
        FruitDescription(fruit)
        Spacer(modifier = Modifier.height(30.dp))
        StartButton(navController,viewModel)
        PageIndicator(pagerState)
    }
}

@Composable
private fun FruitImage(fruit: Fruit) {
    Image(
        painter = painterResource(id = fruit.imageResId),
        contentDescription = fruit.name,
        contentScale = ContentScale.Inside,
        modifier = Modifier
            .size(350.dp)
            .clip(RoundedCornerShape(20.dp))
    )
}

@Composable
private fun FruitName(fruit: Fruit) {
    Text(
        text = fruit.name,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun FruitDescription(fruit: Fruit) {
    Text(
        text = fruit.description,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}

@Composable
fun StartButton(navController: NavController,viewModel: FruitViewModel) {
    Button(
        onClick = { viewModel.show_fruit_list(navController)},
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCE93D8)),
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .padding(top = 16.dp)
    ) {
        Text(
            stringResource(R.string.start),
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicator(pagerState: PagerState) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 15.dp)
            .fillMaxHeight(),
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
