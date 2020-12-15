package com.walidkh.android.myfavcars.models

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userName")var userName: String = "",
    @SerializedName("password")var password: String = ""): BaseObservable()