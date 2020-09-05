package com.ignacio.bikesharing.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ignacio.bikesharing.data.BikesLocalDataSource
import com.ignacio.bikesharing.domain.BikeNetwork

class BikesLocalDataSourceImpl : BikesLocalDataSource {
    override suspend fun saveBikes(networksList: List<BikeNetwork>) {

    }

    override fun getAllBikes(): LiveData<List<BikeNetwork>> {
        return MutableLiveData<List<BikeNetwork>>(listOf())
    }

}