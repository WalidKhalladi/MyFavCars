package com.walidkh.android.myfavcars.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionsViewModel : ViewModel() {

    private val _navigateToCarsList = MutableLiveData<Boolean>()
    val navigateToCarsList : LiveData<Boolean>
        get() = _navigateToCarsList

    fun navigateToCarsListPage() {
        _navigateToCarsList.value = true
    }

    fun navigateToCarsListPageComplete() {
        _navigateToCarsList.value = false
    }
}