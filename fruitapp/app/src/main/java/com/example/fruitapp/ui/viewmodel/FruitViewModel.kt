package com.example.fruitapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fruitapp.data.model.Fruit
import com.example.fruitapp.data.repository.FruitRepository


class FruitViewModel : ViewModel() {
    private val repository = FruitRepository()


    private var _fruit: MutableLiveData<Fruit> = MutableLiveData(null)
    val fruit: LiveData<Fruit> get() = _fruit


    private var _moreFruit: MutableLiveData<List<Fruit>> = MutableLiveData(emptyList())
    val more_fruit: LiveData<List<Fruit>> get() = _moreFruit


    fun getFoodByID(id: Int?) {
        _fruit.value = repository.getFoodByID(id)
    }
    init {
        getAllFruits()
    }


    fun getAllFruits() {
        _moreFruit.value = repository.getALlFruits()
    }
}

