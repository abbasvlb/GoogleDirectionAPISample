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

    suspend fun insertGDirectionResponseToDb(gDirectionResponse: GDirectionResponse)

    suspend fun getAllGDirectionResponses(): Flow<ArrayList<GDirectionResponse>>

    suspend fun getGDirectionById(id: Int): Flow<ApiResponse<GDirectionResponse>>

    suspend fun getCount(): Flow<Int>
}