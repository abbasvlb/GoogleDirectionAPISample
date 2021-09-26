package com.example.gdirectionspoc.ui.location

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gdirectionspoc.R
import com.example.gdirectionspoc.databinding.FragmentLocationBinding
import com.example.gdirectionspoc.di.ApiKey
import com.example.gdirectionspoc.di.PlacesApiKey
import com.example.gdirectionspoc.viewModel.GDirectionViewModel
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class AddLocationFragment: Fragment() {

    private lateinit var binding: FragmentLocationBinding

    companion object{
        private val AUTOCOMPLETE_REQUEST_CODE = 100
    }

    private lateinit var currentWayPointTv: TextView
    private var wayPoints: ArrayList<String> = ArrayList()
    private lateinit var mAutoCompleteFragment: AutocompleteSupportFragment
    private lateinit var fields: List<Place.Field>
    private lateinit var types: List<Place.Type>

    private lateinit var gDirectionViewModel: GDirectionViewModel

    @Inject
    @PlacesApiKey
    lateinit var apiKey: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLocationBinding.inflate(layoutInflater)
        currentWayPointTv = binding.wayPointTv

        gDirectionViewModel = ViewModelProvider(requireActivity()).get(GDirectionViewModel::class.java)

        if(!Places.isInitialized()){
            Log.d("LocationFragment", "startLocationResult: apiKey = $apiKey")
            Places.initialize(requireContext(), apiKey, Locale.ROOT)
        }
        fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS)
        types = listOf(Place.Type.ADMINISTRATIVE_AREA_LEVEL_1, Place.Type.ADMINISTRATIVE_AREA_LEVEL_2, Place.Type.ADMINISTRATIVE_AREA_LEVEL_3, Place.Type.DEPARTMENT_STORE, Place.Type.SHOPPING_MALL, Place.Type.SUPERMARKET, Place.Type.GROCERY_OR_SUPERMARKET)

        mAutoCompleteFragment = childFragmentManager.findFragmentById(binding.autocompleteFragment.id) as AutocompleteSupportFragment

        mAutoCompleteFragment.setPlaceFields(fields)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startLocationTv.setOnClickListener {

            binding.autocompleteFragment.visibility = View.VISIBLE

            mAutoCompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener{
                override fun onPlaceSelected(p0: Place) {
                    binding.autocompleteFragment.visibility = View.GONE
                    binding.startLocationTv.text = p0.address
                }

                override fun onError(p0: Status) {
                    Log.d("LocationFragment", "startLocationResult: data = ${p0.status}")
                }

            })

        }

        currentWayPointTv.setOnClickListener(wayPointTvClickListener)

        binding.saveTv.setOnClickListener {
            getGDirections()
            findNavController().navigate(R.id.action_nav_location_to_nav_map)
        }

    }

    private fun getGDirections() {
        val wayPointStringBuilder: StringBuilder = StringBuilder("optimize:true|")
        wayPoints.forEachIndexed { index, s ->
            if (index < wayPoints.size-1){
                wayPointStringBuilder.append(s).append("|")
            } else {
                wayPointStringBuilder.append(s)
            }
        }

        gDirectionViewModel.getGDirection(binding.startLocationTv.text.toString(), binding.startLocationTv.text.toString(), wayPointStringBuilder.toString())
    }

    private val wayPointTvClickListener = View.OnClickListener {

        binding.autocompleteFragment.visibility = View.VISIBLE

        mAutoCompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener{
            override fun onPlaceSelected(p0: Place) {
                binding.autocompleteFragment.visibility = View.GONE
                currentWayPointTv.text = p0.address
                wayPoints.add(p0.address!!)
                setCurrentWayPoint()
            }

            override fun onError(p0: Status) {

            }

        })
    }

    private fun setCurrentWayPoint() {
        val textView = TextView(requireContext())
        textView.text = getString(R.string.enter_way_points_hint_text)
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(0, 24, 0, 0)
        textView.layoutParams = layoutParams
        textView.setPadding(5, 24, 5, 24)

        binding.wayPointsLl.addView(textView)

        currentWayPointTv = textView

        currentWayPointTv.setOnClickListener(wayPointTvClickListener)
    }
}