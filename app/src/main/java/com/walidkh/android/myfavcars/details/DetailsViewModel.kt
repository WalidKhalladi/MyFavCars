package com.walidkh.android.myfavcars.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.walidkh.android.myfavcars.models.Car

class DetailsViewModel : ViewModel() {

    private val _onConfirm = MutableLiveData<Boolean>()
    val onConfirm : LiveData<Boolean>
        get() = _onConfirm

    private val _onCancel = MutableLiveData<Boolean>()
    val onCancel : LiveData<Boolean>
        get() = _onCancel


    fun onConfirmButtonNavigation() {
        _onConfirm.value = true
    }

    fun onConfirmButtonNavigationComplete() {
        _onConfirm.value = false
    }

    fun onCancelButtonNavigation() {
        _onCancel.value = true
    }

    fun onCancelButtonNavigationComplete() {
        _onCancel.value = false
    }
}