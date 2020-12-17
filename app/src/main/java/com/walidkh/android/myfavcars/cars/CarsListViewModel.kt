package com.walidkh.android.myfavcars.cars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.walidkh.android.myfavcars.models.Car

class CarsListViewModel : ViewModel() {

    var car : Car = Car()
    private val _carsListData = MutableLiveData<Car>()
    val carsListData : LiveData<Car>
        get() = _carsListData

    private val _navigateToAddPage = MutableLiveData<Boolean>()
    val navigateToAddPage : LiveData<Boolean>
        get() = _navigateToAddPage

    fun navigateToAddPage() {
        _navigateToAddPage.value = true
    }

    fun navigateToAddPageComplete() {
        _navigateToAddPage.value = false
    }

}