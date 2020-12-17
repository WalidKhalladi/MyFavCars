package com.walidkh.android.myfavcars.models

data class Car(
    var name: String = "",
    var brand: String = "",
    var type: CarType = CarType.UNKNOWN,
    var horsePower: Int = 100,
    var description: String = ""
)
fun Car.clone(): Car {
    val car = Car()
    car.name = name
    car.brand = brand
    car.type = type
    car.horsePower = horsePower
    car.description = description
    return car
}