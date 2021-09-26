package com.example.gdirectionspoc.ui.editLocation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.gdirectionspoc.R
import com.example.gdirectionspoc.databinding.FragmentEditLocationBinding
import com.example.gdirectionspoc.ui.editLocation.EditLocationFragmentArgs

private const val ID = "id"

class EditLocationFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentEditLocationBinding

    //vars
    private var gId: Int = 0

    //val
    private val args: EditLocationFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gId = args.id
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEditLocationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initViews() {

    }
}