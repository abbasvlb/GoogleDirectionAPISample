package com.example.gdirectionspoc.ui.locationList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdirectionspoc.R
import com.example.gdirectionspoc.databinding.FragmentLocationsListBinding
import com.example.gdirectionspoc.network.ApiResponse
import com.example.gdirectionspoc.pojo.GDirectionResponse
import com.example.gdirectionspoc.ui.locationList.adapter.LocationListItemAdapter
import com.example.gdirectionspoc.ui.locationList.interfaces.LocationsListItemClickListener
import com.example.gdirectionspoc.viewModel.GDirectionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationsListFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentLocationsListBinding

    //vars
    private lateinit var locationListItemAdapter: LocationListItemAdapter

    //variables
    private var locationList: ArrayList<GDirectionResponse?> = ArrayList()

    private val gDirectionViewModel: GDirectionViewModel by lazy{
        ViewModelProvider(requireActivity()).get(GDirectionViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLocationsListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViews()

        setObserver()

        gDirectionViewModel.getAllGDirections()

    }

    private fun setObserver() {
        gDirectionViewModel.gDirectionListMutableLiveData.observe(requireActivity(), {
            when(it){
                is ApiResponse.Error -> {

                }
                is ApiResponse.Loading -> {
                    binding.locationListRv.visibility = View.GONE
                    binding.locationLoadProgressBar.visibility = View.VISIBLE


                }
                is ApiResponse.Success -> {
                    locationList.addAll(it.value)
                }
            }

        })

    }

    private fun setViews() {
        locationListItemAdapter = LocationListItemAdapter(locationList, locationsListItemClickListener)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.locationListRv.layoutManager = layoutManager
        binding.locationListRv.adapter = locationListItemAdapter
    }

    private val locationsListItemClickListener = object : LocationsListItemClickListener{
        override fun onClickItem(id: Int) {

        }

        override fun onClickEdit(id: Int) {

        }

        override fun onClickGetLocation(id: Int) {

        }

    }


}