package com.example.gdirectionspoc.ui.editLocation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdirectionspoc.R
import com.example.gdirectionspoc.databinding.FragmentEditLocationBinding
import com.example.gdirectionspoc.di.PlacesApiKey
import com.example.gdirectionspoc.network.ApiResponse
import com.example.gdirectionspoc.pojo.GDirectionResponse
import com.example.gdirectionspoc.ui.editLocation.adapter.WayPointItemAdapter
import com.example.gdirectionspoc.ui.editLocation.interfaces.WayPointItemClickListener
import com.example.gdirectionspoc.utils.VerticalItemSpaceDecorator
import com.example.gdirectionspoc.viewModel.GDirectionViewModel
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class EditLocationFragment : Fragment() {

    //binding
    private lateinit var fragmentEditLocationBinding: FragmentEditLocationBinding

    //args
    private val args: EditLocationFragmentArgs by navArgs()

    //instances
    private var wayPointItemAdapter: WayPointItemAdapter? = null
    private var gDirectionViewModel: GDirectionViewModel? = null
    private lateinit var mAutoCompleteFragment: AutocompleteSupportFragment

    //vars
    private var gId: Int = 0
    private var wayPointsArrayList: ArrayList<String> = ArrayList()
    private lateinit var fields: List<Place.Field>
    private lateinit var types: List<Place.Type>

    @Inject
    @PlacesApiKey
    lateinit var apiKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gId = args.id
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentEditLocationBinding = FragmentEditLocationBinding.inflate(layoutInflater)
        return fragmentEditLocationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gDirectionViewModel = ViewModelProvider(requireActivity()).get(GDirectionViewModel::class.java)

        if(!Places.isInitialized()){
            Places.initialize(requireContext(), apiKey, Locale.ROOT)
        }
        fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS)
        types = listOf(Place.Type.ADMINISTRATIVE_AREA_LEVEL_1, Place.Type.ADMINISTRATIVE_AREA_LEVEL_2, Place.Type.ADMINISTRATIVE_AREA_LEVEL_3, Place.Type.DEPARTMENT_STORE, Place.Type.SHOPPING_MALL, Place.Type.SUPERMARKET, Place.Type.GROCERY_OR_SUPERMARKET)

        mAutoCompleteFragment = childFragmentManager.findFragmentById(fragmentEditLocationBinding.autocompleteFragment.id) as AutocompleteSupportFragment

        mAutoCompleteFragment.setPlaceFields(fields)

        initRecyclerView()

        setObserver()

        fragmentEditLocationBinding.saveTv.setOnClickListener {
            val wayPointStringBuilder: StringBuilder = StringBuilder("optimize:true|")
            wayPointsArrayList.forEachIndexed { index, s ->
                if (index < wayPointsArrayList.size-1){
                    wayPointStringBuilder.append(s).append("|")
                } else {
                    wayPointStringBuilder.append(s)
                }
            }

            gDirectionViewModel!!.getGDirection(fragmentEditLocationBinding.startLocationTv.text.toString(), fragmentEditLocationBinding.startLocationTv.text.toString(), wayPointStringBuilder.toString())
            findNavController().navigate(R.id.action_nav_add_location_to_nav_map)
        }

        gDirectionViewModel!!.getGDirectionById(gId)

    }

    private fun initRecyclerView() {
        wayPointItemAdapter = WayPointItemAdapter(wayPointsArrayList, wayPointItemClickListener)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        fragmentEditLocationBinding.wayPointsRv.layoutManager = layoutManager
        fragmentEditLocationBinding.wayPointsRv.addItemDecoration(VerticalItemSpaceDecorator(12))
        fragmentEditLocationBinding.wayPointsRv.adapter = wayPointItemAdapter
    }

    private fun setObserver() {
        gDirectionViewModel!!.gDirectionMutableLiveData.observe(requireActivity(), {
            when(it){
                is ApiResponse.Error -> {

                }
                is ApiResponse.Loading -> {

                }
                is ApiResponse.Success -> {
                    setUpViews(it.value!!)

                }
            }
        })
    }

    private fun setUpViews(gDirectionResponse: GDirectionResponse) {

        fragmentEditLocationBinding.startLocationTv.text = gDirectionResponse.routes[0].legs[0].startAddress

        wayPointsArrayList.clear()

        gDirectionResponse.routes[0].legs.forEachIndexed { index, leg ->
            if (index < gDirectionResponse.routes[0].legs.size - 1){
                wayPointsArrayList.add(leg.end_address)
            }
        }

        wayPointItemAdapter!!.notifyDataSetChanged()

    }

    private val wayPointItemClickListener = object : WayPointItemClickListener{
        override fun onAddItemClick() {
            fragmentEditLocationBinding.autocompleteFragment.visibility = View.VISIBLE

            mAutoCompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
                override fun onPlaceSelected(p0: Place) {
                    wayPointsArrayList.add(p0.address!!)
                    wayPointItemAdapter!!.notifyDataSetChanged()
                    fragmentEditLocationBinding.autocompleteFragment.visibility = View.GONE
                }

                override fun onError(p0: Status) {

                }

            })
        }

        override fun onClickDelete(i: Int) {
            wayPointsArrayList.removeAt(i)
            wayPointItemAdapter!!.notifyItemRemoved(i)
        }

    }
}