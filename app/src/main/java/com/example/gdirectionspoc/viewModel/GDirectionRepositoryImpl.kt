package com.example.gdirectionspoc.viewModel

import com.example.gdirectionspoc.di.ApiKey
import com.example.gdirectionspoc.network.ApiResponse
import com.example.gdirectionspoc.network.ApiService
import com.example.gdirectionspoc.pojo.GDirectionResponse
import com.example.gdirectionspoc.pojo.dao.GDirectionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GDirectionRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @ApiKey private val apiKey: String,
    private val gDirectionDao: GDirectionDao
) : GDirectionRepository {

    override suspend fun getGDirections(
        origin: String,
        destination: String,
        wayPoints: String
    ): Flow<ApiResponse<GDirectionResponse?>> = flow {
        emit(ApiResponse.Loading(null))
        try {
            val data = apiService.getGoogleDirection(origin, destination, wayPoints, "BICYCLING", apiKey)
            if (data.isSuccessful) {
                emit(ApiResponse.Success(data.body()))
            } else {
                emit(ApiResponse.Error(null))
            }

        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun insertGDirectionResponseToDb(gDirectionResponse: GDirectionResponse) {
        gDirectionDao.insert(gDirectionResponse)
    }
}