package com.example.fruitapp.data.model

data class Fruit(
    val name: String,
    val description: String,
    val imageResId: Int,
    val detailDescription: String,
    val fruitID: Int,
    val color: androidx.compose.ui.graphics.Color
)
