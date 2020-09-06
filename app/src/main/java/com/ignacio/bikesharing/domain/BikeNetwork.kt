package com.ignacio.bikesharing.domain

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class BikeNetworksResponse(
    @Serializable(with = BikesNetworkDeserializerKotlinx::class)
    val networks: List<BikeNetwork>
)

@Serializable(with = BikesNetworkDeserializerKotlinx::class)
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

@Serializable
data class License(
    val name: String,
    val url: String
)

@Serializable
data class Location(
    val city: String,
    val country: String,
    val latitude: Double,
    val longitude: Double
)

@Serializer(forClass = BikeNetwork::class)
class BikesNetworkDeserializerKotlinx() : KSerializer<BikeNetwork> {
    override fun deserialize(decoder: Decoder): BikeNetwork {
        val jsonDecoder = decoder as JsonDecoder
        val element = jsonDecoder.decodeJsonElement()
        val jsonObject = element.jsonObject
        val licenseElement = jsonObject["license"]
        val license = licenseElement?.let {
            Json.decodeFromJsonElement(License.serializer(), it)
        }
        val locationElement = jsonObject["location"]
        val location = Json.decodeFromJsonElement(Location.serializer(), locationElement!!)

        val companyElement = jsonObject["company"]

        val company =
            when(companyElement) {
                null -> listOf()
                is kotlinx.serialization.json.JsonArray -> companyElement.map { it.jsonPrimitive.content }
                is kotlinx.serialization.json.JsonPrimitive -> listOf(companyElement.content)
                else -> throw IllegalArgumentException("wrong type... is ${companyElement.javaClass.simpleName}")
            }

        println("company: ${company.map { it }}")

        return BikeNetwork(
            company,
            jsonObject["gbfs_href"]?.jsonPrimitive?.content ?: "",
            jsonObject["href"]!!.jsonPrimitive.content,
            jsonObject["id"]!!.jsonPrimitive.content,
            license,
            location,
            jsonObject["name"]!!.jsonPrimitive.content,
            jsonObject["source"]?.jsonPrimitive?.content ?: ""
        )
    }

    override fun serialize(encoder: Encoder, value: BikeNetwork) {
        //encoder.encodeSerializableValue()
    }

    override val descriptor: SerialDescriptor
        get() = BikeNetwork.serializer().descriptor

}