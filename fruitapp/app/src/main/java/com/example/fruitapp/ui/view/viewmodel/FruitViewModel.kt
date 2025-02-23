package com.example.fruitapp.ui.view.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.fruitapp.data.model.Fruit
import com.example.fruitapp.domain.usecase.FruitUseCase




class FruitViewModel : ViewModel() {

    private val repository = FruitUseCase()

    val _fruits = mutableStateListOf<Fruit>()
    val fruits: SnapshotStateList<Fruit> get() = _fruits

    init {
        loadFruits()
    }

    private fun loadFruits() {
        _fruits.clear()
        _fruits.addAll(repository.getALlFruits())
    }

    fun addFruit(fruit: Fruit) {
        repository.addFruit(fruit)
        _fruits.add(fruit)
    }

    fun deleteFruit(fruit: Fruit) {
        repository.deleteFruit(fruit.fruitID)
        _fruits.remove(fruit)
    }

    fun updateSearchQuery(query: String) {
        val allFruits = repository.getALlFruits()
        _fruits.clear()
        _fruits.addAll(
            if (query.isEmpty()) allFruits
            else allFruits.filter { it.name.contains(query, ignoreCase = true) }
        )
    }

    var _fruit: MutableLiveData<Fruit> = MutableLiveData(null)
    val fruit: LiveData<Fruit> get() = _fruit
    fun getFruitByID(id: Int?) {
        _fruit.value = repository.getFruitByID(id)
    }

    fun show_detail_screen(navController: NavController,fruit: Fruit)
    {
        navController.navigate("detail_screen/${fruit.fruitID}")
    }
    fun show_fruit_list(navController: NavController){
        navController.navigate("fruits_list")
    }
}
