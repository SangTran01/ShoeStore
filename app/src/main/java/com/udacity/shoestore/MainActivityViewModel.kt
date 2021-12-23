package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainActivityViewModel : ViewModel() {
    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>> get() = _shoes

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe> get() = _shoe

    init {
        _shoes.value = ArrayList()
    }

    fun addShoe(shoe: Shoe) {
        _shoe.value = shoe
        _shoes.value?.add(shoe)
    }
}