package com.example.airportinfo.model

data class Airport(
    val id: Long?,
    val iata: String?,
    val icao: String?,
    val name: String?,
    val location: String?,
    val street_number: String?,
    val street: String?,
    val city: String?,
    val county: String?,
    val state: String?,
    val country_iso: String?,
    val country: String?,
    val postal_code: String?,
    val phone: String?,
    val latitude: Double?,
    val longitude: Double?,
    val uct: Long?,
    val website: String?
)