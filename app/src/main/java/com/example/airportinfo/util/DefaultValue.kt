package com.example.airportinfo.util

fun String?.defaultIfNull(): String {
    return this ?: ""
}

fun Double?.defaultIfNull(): Double {
    return this ?: 0.0
}