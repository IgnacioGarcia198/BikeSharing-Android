package com.ignacio.bikesharing.data

import androidx.lifecycle.LiveData
import com.ignacio.bikesharing.domain.BikeNetwork

interface BikesRepository {
    val bikesLocalDataSource: BikesLocalDataSource
    val bikesRemoteDataSource: BikesRemoteDataSource

    suspend fun getAllBikeNetworksFromNetwork(): List<BikeNetwork>
    fun getAllBikeNetworksFromDb(): LiveData<List<BikeNetwork>>
    suspend fun updateBikeNetworkwsDb()
}

class BikesRepositoryImpl(
    override val bikesLocalDataSource: BikesLocalDataSource,
    override val bikesRemoteDataSource: BikesRemoteDataSource
): BikesRepository {
    val bikeNetworks = bikesLocalDataSource.getAllBikes()

    override suspend fun getAllBikeNetworksFromNetwork(): List<BikeNetwork> {
        return bikesRemoteDataSource.getAllBikes()
    }

    override fun getAllBikeNetworksFromDb(): LiveData<List<BikeNetwork>> {
        return bikesLocalDataSource.getAllBikes()
    }

    override suspend fun updateBikeNetworkwsDb() {
        val bikeNetworks = bikesRemoteDataSource.getAllBikes()
        bikesLocalDataSource.saveBikes(bikeNetworks)
    }

}

interface BikesLocalDataSource {
    suspend fun saveBikes(networksList: List<BikeNetwork>)
    fun getAllBikes(): LiveData<List<BikeNetwork>>
}

interface BikesRemoteDataSource {
    suspend fun getAllBikes(): List<BikeNetwork>
}