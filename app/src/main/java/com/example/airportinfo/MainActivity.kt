package com.example.airportinfo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.airportinfo.screen.AirportInfo
import com.example.airportinfo.screen.AirportInfoFragment
import com.example.airportinfo.screen.AirportInfoViewModel
import org.koin.android.ext.android.inject

class MainActivity: AppCompatActivity() {
    private val airportInfoViewModel: AirportInfoViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            AirportInfo(airportInfoViewModel)
        }
    }
}

/*
class MainActivity: AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<AirportInfoFragment>(R.id.fcvAirportInfo)
            }
        }
    }
}
*/