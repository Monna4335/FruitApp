package com.example.fruitapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun DetailScreen(navController: NavController, fruit: Fruit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Header(navController)
        Content(fruit)
    }
}

@Composable
fun Header(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Fructose",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        androidx.compose.material3.IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            androidx.compose.material3.Icon(
                painter = painterResource(R.drawable.ic_back_foreground),
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
}

@Composable
fun Content(fruit: Fruit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            DetFruitImage(fruit)
            FruitName(fruit)
            FruitDescription(fruit)
            FruitDetailDescription(fruit)
        }
    }
}

@Composable
private fun DetFruitImage(fruit: Fruit) {
    Spacer(Modifier.height(10.dp))
    Image(
        painter = painterResource(id = fruit.imageResId),
        contentDescription = fruit.name,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(350.dp)

    )
}

@Composable
private fun FruitName(fruit: Fruit) {
    Spacer(Modifier.height(12.dp))
    Text(
        text = fruit.name,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White.copy(0.8f),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    )
}

@Composable
private fun FruitDescription(fruit: Fruit) {
    Spacer(Modifier.height(15.dp))
    Text(
        text = fruit.description,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White.copy(0.8f),
        modifier = Modifier.padding(20.dp)
    )
}

@Composable
fun FruitDetailDescription(fruit: Fruit) {
    Spacer(Modifier.height(5.dp))
    Text(
        text = fruit.detailDescription,
        fontSize = 16.sp,
        color = Color.White.copy(0.8f),
        modifier = Modifier.padding(20.dp, top = 0.dp)
    )
}
