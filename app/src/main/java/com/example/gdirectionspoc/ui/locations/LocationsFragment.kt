package com.example.gdirectionspoc.ui.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdirectionspoc.databinding.FragmentLocationsBinding
import com.example.gdirectionspoc.pojo.GDirectionResponse
import com.example.gdirectionspoc.ui.locations.adapter.LocationItemAdapter
import com.example.gdirectionspoc.ui.locations.interfaces.LocationItemClickListener
import com.example.gdirectionspoc.utils.VerticalItemSpaceDecorator
import com.example.gdirectionspoc.viewModel.GDirectionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationsFragment : Fragment() {

    //binding
    private lateinit var fragmentLocationBinding: FragmentLocationsBinding

    //val
    private var gDirectionViewModel: GDirectionViewModel? = null
    private var locationItemAdapter: LocationItemAdapter? = null
    
    //var
    private var gDirectionsArrayList: ArrayList<GDirectionResponse> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentLocationBinding = FragmentLocationsBinding.inflate(layoutInflater)

        return fragmentLocationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gDirectionViewModel = ViewModelProvider(requireActivity()).get(GDirectionViewModel::class.java)

        initLocationAdapter()
        
        setObservers()

        gDirectionViewModel!!.getAllGDirectionResponses()
        
    }

    private fun initLocationAdapter() {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        locationItemAdapter = LocationItemAdapter(gDirectionsArrayList, locationItemClickListener)
        
        fragmentLocationBinding.locationListRv.layoutManager = layoutManager
        fragmentLocationBinding.locationListRv.addItemDecoration(VerticalItemSpaceDecorator(24))
        fragmentLocationBinding.locationListRv.adapter = locationItemAdapter
    }

    private fun setObservers() {
        gDirectionViewModel!!.gDirectionResponsesMutableLiveData.observe(requireActivity(), {
            gDirectionsArrayList.addAll(it)
            locationItemAdapter!!.notifyDataSetChanged()
        })
    }
    
    private val locationItemClickListener = object : LocationItemClickListener{
        override fun onItemClicked(id: Int) {
            
        }

        override fun onEditClicked(id: Int) {
            
        }

        override fun onGetLocationClicked(id: Int) {
            
        }

    }
}