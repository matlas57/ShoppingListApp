package com.example.shoppinglistapp

import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApiService {

    @GET("maps/api/geocode/json") //define the endpoint and the request type
    suspend fun getAddressFromCoordinates(
        //Define the payload of the http request
        @Query("latlng") latlng: String,
        @Query("key") apiKey: String,
    ) :GeocodingResponse  //GeocodingResponse holds a response from the api
}