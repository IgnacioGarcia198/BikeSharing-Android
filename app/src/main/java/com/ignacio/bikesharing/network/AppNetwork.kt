package com.ignacio.bikesharing.network

import com.google.gson.*
import com.google.gson.JsonArray
import com.google.gson.JsonNull
import com.google.gson.JsonPrimitive
import com.ignacio.bikesharing.BuildConfig
import com.ignacio.bikesharing.domain.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import kotlinx.serialization.json.JsonNull.isString
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.lang.reflect.Type

//interface BikesService {
//    /**
//     * Get all bike networks list.
//     */
//    suspend fun getAllBikeNetworks(): BikeNetworksResponse
//
//}

interface BikesService {
    /**
     * Get all bike networks list.
     */
    @GET("networks")
    suspend fun getAllBikeNetworks(): BikeNetworksResponse

}

/*class BikesServiceImpl: BikesService {
    private val baseUrl = "https://api.citybik.es/v2/networks"
    private val nonStrictJson = Json { isLenient = true; ignoreUnknownKeys = true }
    private val client by lazy {
        HttpClient(CIO) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.INFO
            }
        }
    }

    override suspend fun getAllBikeNetworks(): BikeNetworksResponse {
        return client.get(baseUrl)
    }

}*/

val BikeNetworkDeserializer =
    JsonDeserializer { json, _, _ ->
        val gson = Gson()
        val jsonObject = json.asJsonObject
        val licenseElement = jsonObject.get("license")
        val license: License? = gson.fromJson(licenseElement, License::class.java)
        val locationElement = jsonObject.get("location")
        val location = gson.fromJson(locationElement, Location::class.java)
        val companyElement = jsonObject.get("company")
        val company =
            when(companyElement) {
                is JsonArray -> {
                    companyElement.map { it.asString }
                }
                is JsonPrimitive -> {
                    listOf(companyElement.asString)
                }
                is JsonNull -> listOf()
                else -> throw IllegalArgumentException("wrong type... is ${companyElement.javaClass.simpleName}")
            }

        BikeNetwork(
            company,
            jsonObject["gbfs_href"]?.asString ?: "",
            jsonObject["href"].asString,
            jsonObject["id"].asString,
            license,
            location,
            jsonObject["name"].asString,
            jsonObject["source"]?.asString ?:""
        )
    }

object BikesRetrofit {

    val loglevel = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
    val loggingInterceptor = HttpLoggingInterceptor().apply { level = loglevel }

    // Configure retrofit to parse JSON and use coroutines
    val okHttp = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val gsonBuilder = GsonBuilder().apply {
        registerTypeAdapter(BikeNetwork::class.java, BikeNetworkDeserializer) }
    private val gson = gsonBuilder.create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://api.citybik.es/v2/")
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val bikesApiService: BikesService = retrofit.create(BikesService::class.java)
}

object BikesRetrofitKotlinx {

    val loglevel = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
    val loggingInterceptor = HttpLoggingInterceptor().apply { level = loglevel }

    // Configure retrofit to parse JSON and use coroutines
    val okHttp = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val contentType = "application/json".toMediaType()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://api.citybik.es/v2/")
        .client(okHttp)
        .addConverterFactory(Json.asConverterFactory(contentType))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val bikesApiService: BikesService = retrofit.create(BikesService::class.java)
}