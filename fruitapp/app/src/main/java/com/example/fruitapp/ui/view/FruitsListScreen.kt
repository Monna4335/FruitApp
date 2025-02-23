import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
import com.example.fruitapp.ui.view.viewmodel.FruitViewModel

@Composable
fun FruitsListScreen(navController: NavController, viewModel: FruitViewModel) {
    val fruits by remember { derivedStateOf { viewModel.fruits } }
    var searchQuery by remember { mutableStateOf("") }
    var showAddDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(navController)
        SearchBar(searchQuery, onSearchQueryChanged = { viewModel.updateSearchQuery(it) }, onAddButtonClicked = { showAddDialog = true })
        Spacer(modifier = Modifier.height(20.dp))
        FruitList(fruits, navController, viewModel)
    }

    if (showAddDialog) {
        AddFruitDialog(
            viewModel = viewModel,
            onDismiss = { showAddDialog = false }
        )
    }
}

@Composable
fun TopBar(navController: NavController) {
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
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back_foreground),
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
}

@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onAddButtonClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChanged,
            label = { Text("Search Fruits") },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = onAddButtonClicked) {
            Text("Add Fruit")
        }
    }
}

@Composable
fun FruitList(fruits: List<Fruit>, navController: NavController, viewModel: FruitViewModel) {
    LazyColumn {
        items(fruits) { fruit ->
            FruitListItem(fruit = fruit, navController = navController, viewModel = viewModel)
        }
    }
}

@Composable
fun FruitListItem(fruit: Fruit, navController: NavController, viewModel: FruitViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                viewModel.show_detail_screen(navController,fruit)
            },
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Column {
            Row(modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = painterResource(id = fruit.imageResId),
                    contentDescription = fruit.name,
                    modifier = Modifier.size(125.dp).align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column {
                    FruitNameWithDelete(fruit, viewModel)
                    Spacer(modifier = Modifier.height(8.dp))
                    FruitDescription(fruit)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
fun FruitNameWithDelete(fruit: Fruit, viewModel: FruitViewModel) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = fruit.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        DeleteButton(fruit, viewModel)
    }
}

@Composable
fun DeleteButton(fruit: Fruit, viewModel: FruitViewModel) {
    Image(
        painter = painterResource(id = R.drawable.delete),
        contentDescription = fruit.name,
        modifier = Modifier
            .size(35.dp)
            .clickable { viewModel.deleteFruit(fruit) }
    )
}

@Composable
fun FruitDescription(fruit: Fruit) {
    Text(
        text = fruit.description,
        fontSize = 16.sp,
        color = Color.White
    )
}

@Composable
fun AddFruitDialog(
    viewModel: FruitViewModel,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var detailDescription by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Fruit") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") }
                )
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
                TextField(
                    value = detailDescription,
                    onValueChange = { detailDescription = it },
                    label = { Text("Detail Description") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val newFruit = Fruit(
                    name = name,
                    description = description,
                    detailDescription = detailDescription,
                    imageResId = R.drawable.blueberry,
                    fruitID = viewModel._fruits?.size?.plus(10) ?: 1,
                    color = Color(0xFF000000)
                )
                viewModel.addFruit(newFruit)
                onDismiss()
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
