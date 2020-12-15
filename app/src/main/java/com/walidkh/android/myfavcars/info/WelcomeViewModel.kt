package com.walidkh.android.myfavcars.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {

    private val _navigateToInstructionPage = MutableLiveData<Boolean>()
    val navigateToInstructionPage : LiveData<Boolean>
        get() = _navigateToInstructionPage

    fun navigateToInstructionsPage() {
        _navigateToInstructionPage.value = true
    }

    fun navigateToInstructionsPageComplete() {
        _navigateToInstructionPage.value = false
    }
}