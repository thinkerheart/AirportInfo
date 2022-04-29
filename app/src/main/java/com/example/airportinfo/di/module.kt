package com.example.airportinfo.di

import com.example.airportinfo.data.network.AirportInfoApiClient
import com.example.airportinfo.data.network.api.AirportInfoApi
import com.example.airportinfo.repository.AirportRepository
import com.example.airportinfo.screen.AirportInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AirportInfoApiClient() }
    single { (get() as AirportInfoApiClient).createService(AirportInfoApi::class.java) }
    single { AirportRepository(get()) }
    viewModel { AirportInfoViewModel(get()) }
}