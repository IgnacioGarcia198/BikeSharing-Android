package com.ignacio.bikesharing.domain

data class BikeNetworksResponse(
    val networks: List<BikeNetwork>
)

data class BikeNetwork(
    val company: List<String> = listOf(),
    val gbfs_href: String = "",
    val href: String,
    val id: String,
    val license: License? = null,
    val location: Location,
    val name: String,
    val source: String = ""
)

data class License(
    val name: String,
    val url: String
)

data class Location(
    val city: String,
    val country: String,
    val latitude: Double,
    val longitude: Double
)