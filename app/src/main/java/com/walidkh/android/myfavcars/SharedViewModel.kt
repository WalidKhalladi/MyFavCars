package com.walidkh.android.myfavcars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.walidkh.android.myfavcars.models.Car
import com.walidkh.android.myfavcars.models.clone

class SharedViewModel : ViewModel() {

    var car: Car = Car()

    private val carsList = mutableListOf<Car>()
    private val _carsListData = MutableLiveData<List<Car>>()
    val carsListData: LiveData<List<Car>>
        get() = _carsListData

    fun addCar(): Boolean {

        return if (validateCarEntries()) {
            val newCar = car.clone()
            carsList.add(0, newCar)
            _carsListData.value = carsList

            true
        } else {
            false
        }
    }

    private fun validateCarEntries(): Boolean {
        return car.name.isNotEmpty()
                && car.brand.isNotEmpty()
                && car.description.isNotEmpty()
                && car.horsePower != 0
    }
}