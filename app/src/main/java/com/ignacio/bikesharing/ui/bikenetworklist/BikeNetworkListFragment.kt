package com.ignacio.bikesharing.ui.bikenetworklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ignacio.bikesharing.R

class BikeNetworkListFragment : Fragment() {

    lateinit var viewModel: BikeNetworkListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bike_network_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BikeNetworkListViewModel::class.java)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getAllBikeNetworksFromNetwork()
    }

}