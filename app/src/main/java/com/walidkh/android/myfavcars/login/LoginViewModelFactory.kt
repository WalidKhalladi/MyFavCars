package com.walidkh.android.myfavcars.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.walidkh.android.myfavcars.preferences.PreferencesProvider
import java.lang.IllegalArgumentException

class LoginViewModelFactory (
    private val preferencesProvider: PreferencesProvider
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(preferencesProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}