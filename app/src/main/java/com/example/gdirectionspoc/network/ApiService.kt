package com.example.gdirectionspoc.network

import com.example.gdirectionspoc.pojo.GDirectionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiService {

    @GET("directions/json?")
    suspend fun getGoogleDirection(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("waypoints") waypoints: String,
        @Query("travel_mode") travel_mode: String,
        @Query("key") key: String
    ): Response<GDirectionResponse?>
}