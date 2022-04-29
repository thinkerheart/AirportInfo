package com.example.airportinfo.repository

import com.example.airportinfo.data.Response
import com.example.airportinfo.model.Airport
import com.example.airportinfo.data.network.api.AirportInfoApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class AirportRepository(
    private val airportInfoApi: AirportInfoApi
) {
    fun getAirport(iata: String): Flow<Response<Airport>> {
        return flow {
            emit(Response.Success(airportInfoApi.getAirport(iata)) as Response<Airport>)
        }.catch {
            emit(Response.Error(it.localizedMessage ?: ""))
        }
    }
}