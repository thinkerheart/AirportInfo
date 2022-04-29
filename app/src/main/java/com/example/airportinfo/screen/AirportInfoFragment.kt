package com.example.airportinfo.screen

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.airportinfo.R
import com.example.airportinfo.databinding.FragmentAirportInfoBinding
import org.koin.android.ext.android.inject

class AirportInfoFragment : Fragment(R.layout.fragment_airport_info) {
    private val airportInfoViewModel: AirportInfoViewModel by inject()
    private var fragmentAirportInfoBinding: FragmentAirportInfoBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAirportInfoBinding.bind(view)
        fragmentAirportInfoBinding = binding

        fragmentAirportInfoBinding?.edtIATA?.doOnTextChanged { text, _, _, _ ->
            airportInfoViewModel.keyword.value = text.toString()
        }

        fragmentAirportInfoBinding?.btnSearch?.setOnClickListener {
            airportInfoViewModel.getAirport(lifecycleScope)
        }
        airportInfoViewModel.airport.observe(viewLifecycleOwner) {
            fragmentAirportInfoBinding?.txvName?.text = it.name
            fragmentAirportInfoBinding?.txvLocation?.text = it.location
            fragmentAirportInfoBinding?.txvCity?.text = it.city
            fragmentAirportInfoBinding?.txvState?.text = it.state
            fragmentAirportInfoBinding?.txvCountry?.text = it.country
            fragmentAirportInfoBinding?.txvPostalCode?.text = it.postal_code
            fragmentAirportInfoBinding?.txvPhone?.text = it.phone
            fragmentAirportInfoBinding?.txvLatitude?.text = it.latitude.toString()
            fragmentAirportInfoBinding?.txvLongitude?.text = it.longitude.toString()
            fragmentAirportInfoBinding?.txvWebsite?.text = it.website
        }
    }

    override fun onDestroyView() {
        fragmentAirportInfoBinding = null
        super.onDestroyView()
    }
}