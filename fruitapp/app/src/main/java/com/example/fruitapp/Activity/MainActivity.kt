package com.example.fruitapp.Activity

import FruitsListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.fruitapp.ui.view.DetailScreen
import com.example.fruitapp.ui.view.FruitScreen
import com.example.fruitapp.ui.view.viewmodel.FruitViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel = FruitViewModel()

            NavHost(navController = navController, startDestination = "fruit_screen") {
                composable("fruits_list") {
                    FruitsListScreen(navController, viewModel)
                }
                composable("fruit_screen") {
                    FruitScreen(navController, viewModel)
                }
                composable("detail_screen/{fruitId}") { backStackEntry ->
                    val fruitId = backStackEntry.arguments?.getString("fruitId")?.toIntOrNull()
                    if (fruitId != null) {

                        viewModel.getFruitByID(fruitId)
                    }
                    viewModel._fruit.value?.let { fruit ->
                        DetailScreen(navController, fruit)
                    }
                }
            }
        }
    }
}
