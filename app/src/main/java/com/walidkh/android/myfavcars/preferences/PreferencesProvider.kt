package com.walidkh.android.myfavcars.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.walidkh.android.myfavcars.models.User

private const val CURRENT_SESSION_STATUS : String = "current_session_status"

class PreferencesProvider(
    context: Context
) {
    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveUser(user: User) {
        val userString = Gson().toJson(user)
        preference.edit().putString(user.userName+user.password, userString).apply()
    }

    fun getUser(user: User): User? {
        val userString = preference.getString(user.userName+user.password, null)
        return Gson().fromJson<User>(userString, User::class.java)
    }

    fun isNewUser(user: User): Boolean {
        val userString = preference.getString(user.userName+user.password, null)
        return Gson().fromJson<User>(userString, User::class.java) == null
    }

    fun userConnected(isConnected: Boolean) {
        preference.edit()
            .putBoolean(CURRENT_SESSION_STATUS, isConnected)
            .apply()
    }

    fun isUserConnected(): Boolean {
        return preference.getBoolean(CURRENT_SESSION_STATUS, false)
    }
}