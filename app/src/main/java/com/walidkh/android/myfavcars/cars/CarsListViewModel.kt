package com.walidkh.android.myfavcars.cars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.walidkh.android.myfavcars.models.Car

class CarsListViewModel : ViewModel() {

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