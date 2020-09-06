package com.ignacio.bikesharing

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ignacio.bikesharing.domain.BikeNetwork
import com.ignacio.bikesharing.domain.BikeNetworksResponse
import com.ignacio.bikesharing.network.BikeNetworkDeserializer
import com.ignacio.bikesharing.network.BikesNetworkDeserializerKotlinx
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.parse
import org.junit.Assert
import org.junit.Test

class SerializationTest {
    @Serializable
    data class TestClass3(
        val intvalue: Int,
        val stringvalue: String)

    @Test
    fun `simple kotlin deserialization working without using SerialName annotation`() {
        val json = "{\"intvalue\":1,\"stringvalue\":\"one\"}"

        val testClass3: TestClass3 = Json.decodeFromString(TestClass3.serializer(), json)

        Assert.assertEquals(1, testClass3.intvalue)
        Assert.assertEquals("one", testClass3.stringvalue)
    }

    @Test
    fun `our serialization in app is working on BikeNetwork with Kotlinx`() {
        val json2 = "{\"company\":[\"Gobike A/S\"],\"href\":\"/v2/networks/bycyklen\",\"id\":\"bycyklen\",\"location\":{\"city\":\"Copenhagen\",\"country\":\"DK\",\"latitude\":55.673582,\"longitude\":12.564984},\"name\":\"Bycyklen\"}"
        val bikeNetwork2: BikeNetwork = Json.decodeFromString(BikeNetwork.serializer(), json2)
    }

    @Test
    fun `our serialization in app is working on BikeNetwork with Kotlinx case 2`() {
        val json2 = "{\"company\":\"Gobike A/S\",\"href\":\"/v2/networks/bycyklen\",\"id\":\"bycyklen\",\"location\":{\"city\":\"Copenhagen\",\"country\":\"DK\",\"latitude\":55.673582,\"longitude\":12.564984},\"name\":\"Bycyklen\"}"
        val bikeNetwork2: BikeNetwork = Json.decodeFromString(BikeNetwork.serializer(), json2)
    }

    @Test
    fun `our serialization in app is working on BikeNetwork with Kotlinx with custom serializer`() {
        val json2 = "{\"company\":[\"Gobike A/S\"],\"href\":\"/v2/networks/bycyklen\",\"id\":\"bycyklen\",\"location\":{\"city\":\"Copenhagen\",\"country\":\"DK\",\"latitude\":55.673582,\"longitude\":12.564984},\"name\":\"Bycyklen\"}"
        val bikeNetwork2: BikeNetwork = Json.decodeFromString(BikesNetworkDeserializerKotlinx(), json2)
    }

    @Test
    fun `our serialization in app is working on BikeNetwork with Kotlinx case 2 with custom serializer`() {
        val json2 = "{\"company\":\"Gobike A/S\",\"href\":\"/v2/networks/bycyklen\",\"id\":\"bycyklen\",\"location\":{\"city\":\"Copenhagen\",\"country\":\"DK\",\"latitude\":55.673582,\"longitude\":12.564984},\"name\":\"Bycyklen\"}"
        val bikeNetwork2: BikeNetwork = Json.decodeFromString(BikesNetworkDeserializerKotlinx(), json2)
    }

    @Test
    fun `our serialization in app is working on the whole response with Kotlinx`() {
        val json = "{\"networks\":[{\"company\":[\"\\u0417\\u0410\\u041e \\u00ab\\u0421\\u0438\\u0442\\u0438\\u0411\\u0430\\u0439\\u043a\\u00bb\"],\"href\":\"/v2/networks/velobike-moscow\",\"id\":\"velobike-moscow\",\"location\":{\"city\":\"Moscow\",\"country\":\"RU\",\"latitude\":55.75,\"longitude\":37.616667},\"name\":\"Velobike\"},{\"company\":[\"Gobike A/S\"],\"href\":\"/v2/networks/bycyklen\",\"id\":\"bycyklen\",\"location\":{\"city\":\"Copenhagen\",\"country\":\"DK\",\"latitude\":55.673582,\"longitude\":12.564984},\"name\":\"Bycyklen\"},{\"company\":[\"Gobike A/S\"],\"href\":\"/v2/networks/nu-connect\",\"id\":\"nu-connect\",\"location\":{\"city\":\"Utrecht\",\"country\":\"NL\",\"latitude\":52.117,\"longitude\":5.067},\"name\":\"Nu-Connect\"},{\"company\":[\"Urban Infrastructure Partner\"],\"href\":\"/v2/networks/baerum-bysykkel\",\"id\":\"baerum-bysykkel\",\"location\":{\"city\":\"B\\u00e6rum\",\"country\":\"NO\",\"latitude\":59.89455,\"longitude\":10.546343},\"name\":\"Bysykkel\"},{\"company\":[\"Gobike A/S\"],\"href\":\"/v2/networks/bysykkelen\",\"id\":\"bysykkelen\",\"location\":{\"city\":\"Stavanger\",\"country\":\"NO\",\"latitude\":58.969975,\"longitude\":5.733107},\"name\":\"Bysykkelen\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-a-rua\",\"id\":\"onroll-a-rua\",\"location\":{\"city\":\"A R\\u00faa\",\"country\":\"ES\",\"latitude\":42.392049,\"longitude\":-7.114634},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-albacete\",\"id\":\"onroll-albacete\",\"location\":{\"city\":\"Albacete\",\"country\":\"ES\",\"latitude\":38.98913,\"longitude\":-1.858485},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-alhama-de-murcia\",\"id\":\"onroll-alhama-de-murcia\",\"location\":{\"city\":\"Alhama de Murcia\",\"country\":\"ES\",\"latitude\":37.849831,\"longitude\":-1.424891},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-almunecar\",\"id\":\"onroll-almunecar\",\"location\":{\"city\":\"Almu\\u00f1\\u00e9car\",\"country\":\"ES\",\"latitude\":36.735789,\"longitude\":-3.688828},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-antequera\",\"id\":\"onroll-antequera\",\"location\":{\"city\":\"Antequera\",\"country\":\"ES\",\"latitude\":37.018427,\"longitude\":-4.563775},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-aranda-de-duero\",\"id\":\"onroll-aranda-de-duero\",\"location\":{\"city\":\"Aranda de Duero\",\"country\":\"ES\",\"latitude\":41.667601,\"longitude\":-3.689526},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-aranjuez\",\"id\":\"onroll-aranjuez\",\"location\":{\"city\":\"Aranjuez\",\"country\":\"ES\",\"latitude\":40.03097,\"longitude\":-3.605742},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-badajoz\",\"id\":\"onroll-badajoz\",\"location\":{\"city\":\"Badajoz\",\"country\":\"ES\",\"latitude\":38.873627,\"longitude\":-6.99451},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-baeza\",\"id\":\"onroll-baeza\",\"location\":{\"city\":\"Baeza\",\"country\":\"ES\",\"latitude\":37.99537,\"longitude\":-3.468248},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-benidorm\",\"id\":\"onroll-benidorm\",\"location\":{\"city\":\"Benidorm\",\"country\":\"ES\",\"latitude\":38.541251,\"longitude\":-0.126808},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-blanca\",\"id\":\"onroll-blanca\",\"location\":{\"city\":\"Blanca\",\"country\":\"ES\",\"latitude\":38.178195,\"longitude\":-1.372402},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-cieza\",\"id\":\"onroll-cieza\",\"location\":{\"city\":\"Cieza\",\"country\":\"ES\",\"latitude\":38.239139,\"longitude\":-1.417262},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-don-benito-villanueva\",\"id\":\"onroll-don-benito-villanueva\",\"location\":{\"city\":\"Don Benito - Villanueva\",\"country\":\"ES\",\"latitude\":38.964699,\"longitude\":-5.829693},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-el-campello\",\"id\":\"onroll-el-campello\",\"location\":{\"city\":\"El Campello\",\"country\":\"ES\",\"latitude\":38.419616,\"longitude\":-0.398388},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-elche\",\"id\":\"onroll-elche\",\"location\":{\"city\":\"Elche\",\"country\":\"ES\",\"latitude\":38.2668,\"longitude\":-0.696217},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-guadalajara\",\"id\":\"onroll-guadalajara\",\"location\":{\"city\":\"Guadalajara\",\"country\":\"ES\",\"latitude\":40.633021,\"longitude\":-3.166097},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-jaen\",\"id\":\"onroll-jaen\",\"location\":{\"city\":\"Ja\\u00e9n\",\"country\":\"ES\",\"latitude\":37.777738,\"longitude\":-3.790758},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-lalin\",\"id\":\"onroll-lalin\",\"location\":{\"city\":\"Lal\\u00edn\",\"country\":\"ES\",\"latitude\":42.658626,\"longitude\":-8.114976},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-las-palmas\",\"id\":\"onroll-las-palmas\",\"location\":{\"city\":\"Las Palmas de Gran Canaria\",\"country\":\"ES\",\"latitude\":28.124302,\"longitude\":-15.425994},\"name\":\"Onroll\"}]}"
        val bikeNetworkResponse: BikeNetworksResponse = Json.decodeFromString(BikeNetworksResponse.serializer(), json)
        println("???")
    }

    data class TestClass4(
        val intvalue: Int,
        val stringvalue: String)

    @Test
    fun `simple gson deserialization working`() {
        val json = "{\"intvalue\":1,\"stringvalue\":\"one\"}"

        val testClass4: TestClass4 = Gson().fromJson(json, TestClass4::class.java)

        Assert.assertEquals(1, testClass4.intvalue)
        Assert.assertEquals("one", testClass4.stringvalue)
    }

    @Test
    fun `our serialization in app is working on BikeNetwork`() {
        val json2 = "{\"company\":[\"Gobike A/S\"],\"href\":\"/v2/networks/bycyklen\",\"id\":\"bycyklen\",\"location\":{\"city\":\"Copenhagen\",\"country\":\"DK\",\"latitude\":55.673582,\"longitude\":12.564984},\"name\":\"Bycyklen\"}"
        val bikeNetwork2: BikeNetwork = Gson().fromJson(json2, BikeNetwork::class.java)
        println("==== company: ${bikeNetwork2.company.map { it }}, location: ${bikeNetwork2.location.city}")
    }

    @Test
    fun `our serialization in app is working on the whole response with Gson`() {
        val json = "{\"networks\":[{\"company\":[\"\\u0417\\u0410\\u041e \\u00ab\\u0421\\u0438\\u0442\\u0438\\u0411\\u0430\\u0439\\u043a\\u00bb\"],\"href\":\"/v2/networks/velobike-moscow\",\"id\":\"velobike-moscow\",\"location\":{\"city\":\"Moscow\",\"country\":\"RU\",\"latitude\":55.75,\"longitude\":37.616667},\"name\":\"Velobike\"},{\"company\":[\"Gobike A/S\"],\"href\":\"/v2/networks/bycyklen\",\"id\":\"bycyklen\",\"location\":{\"city\":\"Copenhagen\",\"country\":\"DK\",\"latitude\":55.673582,\"longitude\":12.564984},\"name\":\"Bycyklen\"},{\"company\":[\"Gobike A/S\"],\"href\":\"/v2/networks/nu-connect\",\"id\":\"nu-connect\",\"location\":{\"city\":\"Utrecht\",\"country\":\"NL\",\"latitude\":52.117,\"longitude\":5.067},\"name\":\"Nu-Connect\"},{\"company\":[\"Urban Infrastructure Partner\"],\"href\":\"/v2/networks/baerum-bysykkel\",\"id\":\"baerum-bysykkel\",\"location\":{\"city\":\"B\\u00e6rum\",\"country\":\"NO\",\"latitude\":59.89455,\"longitude\":10.546343},\"name\":\"Bysykkel\"},{\"company\":[\"Gobike A/S\"],\"href\":\"/v2/networks/bysykkelen\",\"id\":\"bysykkelen\",\"location\":{\"city\":\"Stavanger\",\"country\":\"NO\",\"latitude\":58.969975,\"longitude\":5.733107},\"name\":\"Bysykkelen\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-a-rua\",\"id\":\"onroll-a-rua\",\"location\":{\"city\":\"A R\\u00faa\",\"country\":\"ES\",\"latitude\":42.392049,\"longitude\":-7.114634},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-albacete\",\"id\":\"onroll-albacete\",\"location\":{\"city\":\"Albacete\",\"country\":\"ES\",\"latitude\":38.98913,\"longitude\":-1.858485},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-alhama-de-murcia\",\"id\":\"onroll-alhama-de-murcia\",\"location\":{\"city\":\"Alhama de Murcia\",\"country\":\"ES\",\"latitude\":37.849831,\"longitude\":-1.424891},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-almunecar\",\"id\":\"onroll-almunecar\",\"location\":{\"city\":\"Almu\\u00f1\\u00e9car\",\"country\":\"ES\",\"latitude\":36.735789,\"longitude\":-3.688828},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-antequera\",\"id\":\"onroll-antequera\",\"location\":{\"city\":\"Antequera\",\"country\":\"ES\",\"latitude\":37.018427,\"longitude\":-4.563775},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-aranda-de-duero\",\"id\":\"onroll-aranda-de-duero\",\"location\":{\"city\":\"Aranda de Duero\",\"country\":\"ES\",\"latitude\":41.667601,\"longitude\":-3.689526},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-aranjuez\",\"id\":\"onroll-aranjuez\",\"location\":{\"city\":\"Aranjuez\",\"country\":\"ES\",\"latitude\":40.03097,\"longitude\":-3.605742},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-badajoz\",\"id\":\"onroll-badajoz\",\"location\":{\"city\":\"Badajoz\",\"country\":\"ES\",\"latitude\":38.873627,\"longitude\":-6.99451},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-baeza\",\"id\":\"onroll-baeza\",\"location\":{\"city\":\"Baeza\",\"country\":\"ES\",\"latitude\":37.99537,\"longitude\":-3.468248},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-benidorm\",\"id\":\"onroll-benidorm\",\"location\":{\"city\":\"Benidorm\",\"country\":\"ES\",\"latitude\":38.541251,\"longitude\":-0.126808},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-blanca\",\"id\":\"onroll-blanca\",\"location\":{\"city\":\"Blanca\",\"country\":\"ES\",\"latitude\":38.178195,\"longitude\":-1.372402},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-cieza\",\"id\":\"onroll-cieza\",\"location\":{\"city\":\"Cieza\",\"country\":\"ES\",\"latitude\":38.239139,\"longitude\":-1.417262},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-don-benito-villanueva\",\"id\":\"onroll-don-benito-villanueva\",\"location\":{\"city\":\"Don Benito - Villanueva\",\"country\":\"ES\",\"latitude\":38.964699,\"longitude\":-5.829693},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-el-campello\",\"id\":\"onroll-el-campello\",\"location\":{\"city\":\"El Campello\",\"country\":\"ES\",\"latitude\":38.419616,\"longitude\":-0.398388},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-elche\",\"id\":\"onroll-elche\",\"location\":{\"city\":\"Elche\",\"country\":\"ES\",\"latitude\":38.2668,\"longitude\":-0.696217},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-guadalajara\",\"id\":\"onroll-guadalajara\",\"location\":{\"city\":\"Guadalajara\",\"country\":\"ES\",\"latitude\":40.633021,\"longitude\":-3.166097},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-jaen\",\"id\":\"onroll-jaen\",\"location\":{\"city\":\"Ja\\u00e9n\",\"country\":\"ES\",\"latitude\":37.777738,\"longitude\":-3.790758},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-lalin\",\"id\":\"onroll-lalin\",\"location\":{\"city\":\"Lal\\u00edn\",\"country\":\"ES\",\"latitude\":42.658626,\"longitude\":-8.114976},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-las-palmas\",\"id\":\"onroll-las-palmas\",\"location\":{\"city\":\"Las Palmas de Gran Canaria\",\"country\":\"ES\",\"latitude\":28.124302,\"longitude\":-15.425994},\"name\":\"Onroll\"}]}"
        val bikeNetworkResponse: BikeNetworksResponse = Gson().fromJson(json, BikeNetworksResponse::class.java)
    }

    @Test
    fun bikeNetworkDeserializerTestGson() {
        val json = "{\"company\":[\"Gobike A/S\"],\"href\":\"/v2/networks/bycyklen\",\"id\":\"bycyklen\",\"location\":{\"city\":\"Copenhagen\",\"country\":\"DK\",\"latitude\":55.673582,\"longitude\":12.564984},\"name\":\"Bycyklen\"}"
        val gsonBuilder = GsonBuilder().apply { registerTypeAdapter(BikeNetwork::class.java, BikeNetworkDeserializer) }
        val bikeNetwork2: BikeNetwork = gsonBuilder.create().fromJson(json, BikeNetwork::class.java)
        println("==== company: ${bikeNetwork2.company.map { it }}, location: ${bikeNetwork2.location.city}")
    }

    @Test
    fun bikeNetworkDeserializerTest2Gson() {
        val json = "{\"company\":\"Gobike A/S\",\"href\":\"/v2/networks/bycyklen\",\"id\":\"bycyklen\",\"location\":{\"city\":\"Copenhagen\",\"country\":\"DK\",\"latitude\":55.673582,\"longitude\":12.564984},\"name\":\"Bycyklen\"}"
        val gsonBuilder = GsonBuilder().apply { registerTypeAdapter(BikeNetwork::class.java, BikeNetworkDeserializer) }
        val bikeNetwork2: BikeNetwork = gsonBuilder.create().fromJson(json, BikeNetwork::class.java)
        println("==== company: ${bikeNetwork2.company.map { it }}, location: ${bikeNetwork2.location.city}")
    }

    @Test
    fun `our serialization in app is working on the whole response with custom deserializer Gson`() {
        val json = "{\"networks\":[{\"company\":[\"\\u0417\\u0410\\u041e \\u00ab\\u0421\\u0438\\u0442\\u0438\\u0411\\u0430\\u0439\\u043a\\u00bb\"],\"href\":\"/v2/networks/velobike-moscow\",\"id\":\"velobike-moscow\",\"location\":{\"city\":\"Moscow\",\"country\":\"RU\",\"latitude\":55.75,\"longitude\":37.616667},\"name\":\"Velobike\"},{\"company\":[\"Gobike A/S\"],\"href\":\"/v2/networks/bycyklen\",\"id\":\"bycyklen\",\"location\":{\"city\":\"Copenhagen\",\"country\":\"DK\",\"latitude\":55.673582,\"longitude\":12.564984},\"name\":\"Bycyklen\"},{\"company\":[\"Gobike A/S\"],\"href\":\"/v2/networks/nu-connect\",\"id\":\"nu-connect\",\"location\":{\"city\":\"Utrecht\",\"country\":\"NL\",\"latitude\":52.117,\"longitude\":5.067},\"name\":\"Nu-Connect\"},{\"company\":[\"Urban Infrastructure Partner\"],\"href\":\"/v2/networks/baerum-bysykkel\",\"id\":\"baerum-bysykkel\",\"location\":{\"city\":\"B\\u00e6rum\",\"country\":\"NO\",\"latitude\":59.89455,\"longitude\":10.546343},\"name\":\"Bysykkel\"},{\"company\":[\"Gobike A/S\"],\"href\":\"/v2/networks/bysykkelen\",\"id\":\"bysykkelen\",\"location\":{\"city\":\"Stavanger\",\"country\":\"NO\",\"latitude\":58.969975,\"longitude\":5.733107},\"name\":\"Bysykkelen\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-a-rua\",\"id\":\"onroll-a-rua\",\"location\":{\"city\":\"A R\\u00faa\",\"country\":\"ES\",\"latitude\":42.392049,\"longitude\":-7.114634},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-albacete\",\"id\":\"onroll-albacete\",\"location\":{\"city\":\"Albacete\",\"country\":\"ES\",\"latitude\":38.98913,\"longitude\":-1.858485},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-alhama-de-murcia\",\"id\":\"onroll-alhama-de-murcia\",\"location\":{\"city\":\"Alhama de Murcia\",\"country\":\"ES\",\"latitude\":37.849831,\"longitude\":-1.424891},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-almunecar\",\"id\":\"onroll-almunecar\",\"location\":{\"city\":\"Almu\\u00f1\\u00e9car\",\"country\":\"ES\",\"latitude\":36.735789,\"longitude\":-3.688828},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-antequera\",\"id\":\"onroll-antequera\",\"location\":{\"city\":\"Antequera\",\"country\":\"ES\",\"latitude\":37.018427,\"longitude\":-4.563775},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-aranda-de-duero\",\"id\":\"onroll-aranda-de-duero\",\"location\":{\"city\":\"Aranda de Duero\",\"country\":\"ES\",\"latitude\":41.667601,\"longitude\":-3.689526},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-aranjuez\",\"id\":\"onroll-aranjuez\",\"location\":{\"city\":\"Aranjuez\",\"country\":\"ES\",\"latitude\":40.03097,\"longitude\":-3.605742},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-badajoz\",\"id\":\"onroll-badajoz\",\"location\":{\"city\":\"Badajoz\",\"country\":\"ES\",\"latitude\":38.873627,\"longitude\":-6.99451},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-baeza\",\"id\":\"onroll-baeza\",\"location\":{\"city\":\"Baeza\",\"country\":\"ES\",\"latitude\":37.99537,\"longitude\":-3.468248},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-benidorm\",\"id\":\"onroll-benidorm\",\"location\":{\"city\":\"Benidorm\",\"country\":\"ES\",\"latitude\":38.541251,\"longitude\":-0.126808},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-blanca\",\"id\":\"onroll-blanca\",\"location\":{\"city\":\"Blanca\",\"country\":\"ES\",\"latitude\":38.178195,\"longitude\":-1.372402},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-cieza\",\"id\":\"onroll-cieza\",\"location\":{\"city\":\"Cieza\",\"country\":\"ES\",\"latitude\":38.239139,\"longitude\":-1.417262},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-don-benito-villanueva\",\"id\":\"onroll-don-benito-villanueva\",\"location\":{\"city\":\"Don Benito - Villanueva\",\"country\":\"ES\",\"latitude\":38.964699,\"longitude\":-5.829693},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-el-campello\",\"id\":\"onroll-el-campello\",\"location\":{\"city\":\"El Campello\",\"country\":\"ES\",\"latitude\":38.419616,\"longitude\":-0.398388},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-elche\",\"id\":\"onroll-elche\",\"location\":{\"city\":\"Elche\",\"country\":\"ES\",\"latitude\":38.2668,\"longitude\":-0.696217},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-guadalajara\",\"id\":\"onroll-guadalajara\",\"location\":{\"city\":\"Guadalajara\",\"country\":\"ES\",\"latitude\":40.633021,\"longitude\":-3.166097},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-jaen\",\"id\":\"onroll-jaen\",\"location\":{\"city\":\"Ja\\u00e9n\",\"country\":\"ES\",\"latitude\":37.777738,\"longitude\":-3.790758},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-lalin\",\"id\":\"onroll-lalin\",\"location\":{\"city\":\"Lal\\u00edn\",\"country\":\"ES\",\"latitude\":42.658626,\"longitude\":-8.114976},\"name\":\"Onroll\"},{\"company\":[\"Domoblue\"],\"href\":\"/v2/networks/onroll-las-palmas\",\"id\":\"onroll-las-palmas\",\"location\":{\"city\":\"Las Palmas de Gran Canaria\",\"country\":\"ES\",\"latitude\":28.124302,\"longitude\":-15.425994},\"name\":\"Onroll\"}]}"

        val gsonBuilder = GsonBuilder().apply { registerTypeAdapter(BikeNetwork::class.java, BikeNetworkDeserializer) }
        //val bikeNetwork2: BikeNetwork = gsonBuilder.create().fromJson(json, BikeNetwork::class.java)
        val bikeNetworkResponse: BikeNetworksResponse = gsonBuilder.create().fromJson(json, BikeNetworksResponse::class.java)
        println("==== response: ${bikeNetworkResponse.networks.first().location.city}")
    }
}