package com.ignacio.bikesharing.network

import com.ignacio.bikesharing.data.BikesRemoteDataSource
import com.ignacio.bikesharing.domain.BikeNetwork

class BikesRemoteDataSourceImpl(private val bikesService: BikesService): BikesRemoteDataSource {
    override suspend fun getAllBikes(): List<BikeNetwork> {
        return bikesService.getAllBikeNetworks().networks
    }
}