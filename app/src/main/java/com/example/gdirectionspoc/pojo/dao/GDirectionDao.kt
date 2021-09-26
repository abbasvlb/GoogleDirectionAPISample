package com.example.gdirectionspoc.pojo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.gdirectionspoc.pojo.GDirectionResponse

@Dao
interface GDirectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gDirectionResponse: GDirectionResponse)
}