package com.example.gdirectionspoc.pojo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gdirectionspoc.pojo.GDirectionResponse

@Dao
interface GDirectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gDirectionResponse: GDirectionResponse)

    @Query("Select * from g_direction_response_table")
    suspend fun getAllGDirectionResponses(): List<GDirectionResponse>

    @Query("Select * from g_direction_response_table Where id =:id ")
    suspend fun getGDirectionById(id: Int): GDirectionResponse

    @Query("Select count(*) from g_direction_response_table")
    suspend fun getCount(): Int
}