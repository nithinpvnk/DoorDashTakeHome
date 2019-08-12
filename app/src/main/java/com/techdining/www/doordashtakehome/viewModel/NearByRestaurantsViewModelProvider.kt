package com.techdining.www.doordashtakehome.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NearByRestaurantsViewModelProvider : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NearByRestaurantsViewModel::class.java)) {
            return NearByRestaurantsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}