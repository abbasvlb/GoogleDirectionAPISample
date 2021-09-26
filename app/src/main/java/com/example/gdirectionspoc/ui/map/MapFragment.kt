package com.example.gdirectionspoc.ui.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.PixelCopy
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gdirectionspoc.R
import com.example.gdirectionspoc.databinding.FragmentMapBinding
import com.example.gdirectionspoc.databinding.LayoutMapMarkerBinding
import com.example.gdirectionspoc.network.ApiResponse
import com.example.gdirectionspoc.pojo.GDirectionResponse
import com.example.gdirectionspoc.viewModel.GDirectionViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.maps.android.PolyUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment() {

    private lateinit var binding: FragmentMapBinding
    private lateinit var layoutMapMarkerBinding: LayoutMapMarkerBinding

    //vars
    private var map: GoogleMap? = null

    private var gDirectionViewModel: GDirectionViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMapBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("MapFragment", "onViewCreated: onViewCreated")

        binding.directionLoadProgressBar.visibility = View.VISIBLE

        gDirectionViewModel = ViewModelProvider(requireActivity()).get(GDirectionViewModel::class.java)

        val mapFragment: SupportMapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(onMapReadyCallback)
    }

    private fun setObserver() {
        gDirectionViewModel!!.gDirectionMutableLiveData.observe(requireActivity(), {
            when (it) {
                is ApiResponse.Error -> {
                    binding.directionLoadProgressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Please check your internet",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is ApiResponse.Loading -> {
                    binding.directionLoadProgressBar.visibility = View.VISIBLE
                }
                is ApiResponse.Success -> {
                    binding.directionLoadProgressBar.visibility = View.GONE
                    if (it.value!!.status == "OK") {
                        setMapDirection(it.value)
                        gDirectionViewModel!!.insertGDirectionToDb(it.value)
                    }
                }
            }

        })
    }

    private fun setMapDirection(value: GDirectionResponse?) {
        layoutMapMarkerBinding = LayoutMapMarkerBinding.inflate(layoutInflater)
        val builder = LatLngBounds.builder()
        value!!.routes[0].legs.forEachIndexed { index, leg ->

            map!!.addMarker(
                MarkerOptions()
                    .position(LatLng(leg.startLocation.lat, leg.startLocation.lang))
                    .anchor(0.5f, 0.5f)
                        .title((index+1).toString())
                    .icon(BitmapDescriptorFactory.fromBitmap(getIconDescriptor((index+1))))
            )
            builder.include(LatLng(leg.startLocation.lat, leg.startLocation.lang))
        }

        val decodedPath = PolyUtil.decode(value.routes[0].overviewPolyline.points)
        map!!.addPolyline(PolylineOptions().addAll(decodedPath))

        //creating boundries for all the markers
        val bounds = builder.build()
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, 100)

        map!!.animateCamera(cu)
    }

    private fun getIconDescriptor(i: Int): Bitmap {
        layoutMapMarkerBinding.mapMarkerTv.text = i.toString()

        layoutMapMarkerBinding.root.layoutParams = ViewGroup.LayoutParams(24, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutMapMarkerBinding.root.measure(100, 100)
        layoutMapMarkerBinding.root.layout(0, 0, 100, 100)
        layoutMapMarkerBinding.root.buildDrawingCache()
        val bitmap = Bitmap.createBitmap(layoutMapMarkerBinding.root.measuredWidth, layoutMapMarkerBinding.root.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        layoutMapMarkerBinding.root.draw(canvas)

        return bitmap
    }

    private val onMapReadyCallback = OnMapReadyCallback {
        map = it

        setObserver()
    }
}