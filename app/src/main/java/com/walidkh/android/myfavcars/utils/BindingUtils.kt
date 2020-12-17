@file:JvmName("BindingUtils")
package com.walidkh.android.myfavcars.utils

import androidx.databinding.InverseMethod
import com.walidkh.android.myfavcars.models.CarType

@InverseMethod("positionToType")
fun carTypeToPosition(type: CarType?): Int {
    return type?.ordinal ?: 0
}

fun positionToType(position: Int): CarType {
    return CarType.values()[position]
}