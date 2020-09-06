package com.ignacio.bikesharing.ui.bikenetworklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ignacio.bikesharing.data.BikesLocalDataSource
import com.ignacio.bikesharing.data.BikesRemoteDataSource
import com.ignacio.bikesharing.data.BikesRepository
import com.ignacio.bikesharing.data.BikesRepositoryImpl
import com.ignacio.bikesharing.db.BikesLocalDataSourceImpl
import com.ignacio.bikesharing.network.BikesRemoteDataSourceImpl
import com.ignacio.bikesharing.network.BikesRetrofit
import com.ignacio.bikesharing.network.BikesRetrofitKotlinx
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class BikeNetworkListViewModel: ViewModel() {
    val remoteDataSource: BikesRemoteDataSource = BikesRemoteDataSourceImpl(BikesRetrofitKotlinx.bikesApiService)
    val localDataSource: BikesLocalDataSource = BikesLocalDataSourceImpl()
    val repository: BikesRepository = BikesRepositoryImpl(
        localDataSource, remoteDataSource
    )

    fun getAllBikeNetworksFromNetwork() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val allNetworks = repository.getAllBikeNetworksFromNetwork()
            Timber.d("==== allnetworks: ${allNetworks.map { it }}")
        }
    }
}