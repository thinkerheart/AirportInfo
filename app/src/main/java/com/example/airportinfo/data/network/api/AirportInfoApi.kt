package com.example.airportinfo.data.network.api

import com.example.airportinfo.model.Airport
import retrofit2.http.GET
import retrofit2.http.Query

interface AirportInfoApi {
    @GET("airport")
    suspend fun getAirport(
        @Query("iata") iata: String
    ): Airport?
}