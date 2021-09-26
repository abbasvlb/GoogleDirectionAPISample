package com.example.gdirectionspoc.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gdirectionspoc.pojo.GDirectionResponse
import com.example.gdirectionspoc.pojo.dao.GDirectionDao

@Database(entities = [GDirectionResponse::class], version = 1, exportSchema = false)
@TypeConverters(DataConvertors::class)
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun gDirectionDao(): GDirectionDao
}