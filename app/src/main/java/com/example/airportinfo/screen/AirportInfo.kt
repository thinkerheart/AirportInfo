package com.example.airportinfo.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.example.airportinfo.data.Response
import com.example.airportinfo.model.Airport
import com.example.airportinfo.repository.AirportRepository
import com.example.airportinfo.util.defaultIfNull
import com.example.airportinfo.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AirportInfoViewModel(
    private val airportRepository: AirportRepository
) : BaseViewModel() {

    val airport = MutableLiveData<Airport>()
    val keyword = MutableLiveData<String>()

    fun getAirport(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            try {
                airportRepository.getAirport(keyword.value.defaultIfNull()).collect {
                    if (it is Response.Success)
                        airport.value = it.data
                    else if (it is Response.Error)
                        error.value = it.errorValue
                }
            } catch (e: Exception) {
                error.value = e.localizedMessage
            }
        }
    }
}

@Composable
fun AirportInfo(airportInfoViewModel: AirportInfoViewModel) {
    val airport = airportInfoViewModel.airport.observeAsState()
    val keyword = airportInfoViewModel.keyword.observeAsState("")
    val coroutineScope = rememberCoroutineScope()

    Scaffold {
        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                OutlinedTextField(
                    value = keyword.value,
                    onValueChange = {
                        airportInfoViewModel.keyword.value = it.trim()
                    },
                    singleLine = true,
                    label = { Text(text = "Type IATA here ...") }
                )
                Button(
                    onClick = { airportInfoViewModel.getAirport(coroutineScope) },
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(text = "Search")
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(0.dp, 30.dp, 0.dp, 0.dp)
            ) {
                Text(text = "Name: ", fontSize = 16.sp)
                Text(text = airport.value?.name.defaultIfNull(), fontSize = 16.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                Text(text = "Location: ", fontSize = 16.sp)
                Text(text = airport.value?.location.defaultIfNull(), fontSize = 16.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                Text(text = "City: ", fontSize = 16.sp)
                Text(text = airport.value?.city.defaultIfNull(), fontSize = 16.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                Text(text = "State: ", fontSize = 16.sp)
                Text(text = airport.value?.state.defaultIfNull(), fontSize = 16.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                Text(text = "Country: ", fontSize = 16.sp)
                Text(text = airport.value?.country.defaultIfNull(), fontSize = 16.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                Text(text = "Postal Code: ", fontSize = 16.sp)
                Text(text = airport.value?.postal_code.defaultIfNull(), fontSize = 16.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                Text(text = "Phone: ", fontSize = 16.sp)
                Text(text = airport.value?.phone.defaultIfNull(), fontSize = 16.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                Text(text = "Latitude: ", fontSize = 16.sp)
                Text(text = airport.value?.latitude.defaultIfNull().toString(), fontSize = 16.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                Text(text = "Longitude: ", fontSize = 16.sp)
                Text(text = airport.value?.longitude.defaultIfNull().toString(), fontSize = 16.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                Text(text = "Website: ", fontSize = 16.sp)
                Text(text = airport.value?.website.defaultIfNull(), fontSize = 16.sp)
            }
        }
    }
}