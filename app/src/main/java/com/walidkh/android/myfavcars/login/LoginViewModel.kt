package com.walidkh.android.myfavcars.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.walidkh.android.myfavcars.models.User
import com.walidkh.android.myfavcars.preferences.PreferencesProvider

class LoginViewModel(
    private val preferencesProvider: PreferencesProvider
) : ViewModel() {

    val user = User()

    private val _showError = MutableLiveData<String>()
    val showError : LiveData<String>
        get() = _showError

    private val isNewUser: Boolean by lazy {
        preferencesProvider.isNewUser(user)
    }

    private val _navigateToWelcomePage = MutableLiveData<Boolean>()
    val navigateToWelcomePage : LiveData<Boolean>
        get() = _navigateToWelcomePage

    private val _navigateToCarsList = MutableLiveData<Boolean>()
    val navigateToCarsList : LiveData<Boolean>
        get() = _navigateToCarsList


    fun navigateToWelcomePage() {
        if (isFormValid()) {
            if (!isNewUser) {
                _showError.value = "User already exists, please sign in"
            } else {
                saveNewUser()
                _navigateToWelcomePage.value = true
            }
        }
    }

    fun navigateToWelcomePageComplete() {
        _navigateToWelcomePage.value = false
    }

    fun navigateToCarsList() {
        if (isFormValid()) {
            if (isNewUser) {
                _showError.value = "user does not exist, please sign up first"
            } else {
                _navigateToCarsList.value = true
            }
        }
    }

    fun navigateToCarsListComplete() {
        _navigateToCarsList.value = false
    }

    private fun saveNewUser() {
        preferencesProvider.saveUser(user)
    }

    private fun isFormValid(): Boolean {
        return if (!(user.userName.isEmpty() || user.password.isEmpty())) {
            _showError.value = ""
            true
        } else {
            _showError.value = "Please fill all inputs"

            false
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}