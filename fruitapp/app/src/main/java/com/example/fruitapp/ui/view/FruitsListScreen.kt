import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fruitapp.R
import com.example.fruitapp.data.model.Fruit
import com.example.fruitapp.ui.viewmodel.FruitViewModel

@Composable
fun FruitsListScreen(navController: NavController, viewModel: FruitViewModel) {

    val fruits = viewModel.more_fruit.observeAsState(initial = emptyList())


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Fructus",
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
        Text(
            text = "Fruits",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFCE93D8),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(fruits.value) { fruit ->
                FruitListItem(fruit = fruit, navController = navController)
            }
        }
    }
}

@Composable
fun FruitListItem(fruit: Fruit, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {

                navController.navigate("detail_screen/${fruit.fruitID}")

            },
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {

            Image(
                painter = painterResource(id = fruit.imageResId),
                contentDescription = fruit.name,
                modifier = Modifier.size(125.dp).align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                Text(
                    text = fruit.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = fruit.description,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}
