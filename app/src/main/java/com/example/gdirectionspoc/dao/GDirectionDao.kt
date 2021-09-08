package com.example.gdirectionspoc.dao

import androidx.room.*
import com.example.gdirectionspoc.db.GDirectionArrayListConvertor
import com.example.gdirectionspoc.pojo.GDirectionResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface GDirectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gDirectionResponse: GDirectionResponse)

//    @Query("SELECT * FROM g_direction_table WHERE id =:id ")
//    suspend fun getGDirection(id : Int): Flow<GDirectionResponse>

//    @Query("SELECT * FROM g_direction_table")
//    suspend fun getAllGDirections():ArrayList<GDirectionResponse?>

}