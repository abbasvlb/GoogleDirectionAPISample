package com.example.gdirectionspoc.viewModel

import com.example.gdirectionspoc.network.ApiResponse
import com.example.gdirectionspoc.pojo.GDirectionResponse
import kotlinx.coroutines.flow.Flow

interface GDirectionRepository {
    suspend fun getGDirections(
        origin: String,
        destination: String,
        wayPoints: String
    ): Flow<ApiResponse<GDirectionResponse?>>

    suspend fun insertGDirectionToDb(gDirectionResponse: GDirectionResponse)

//    suspend fun getGDirectionsFromDb(): Flow<ApiResponse<ArrayList<GDirectionResponse?>>>
}