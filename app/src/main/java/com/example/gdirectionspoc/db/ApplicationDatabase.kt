package com.example.gdirectionspoc.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.gdirectionspoc.dao.GDirectionDao
import com.example.gdirectionspoc.pojo.GDirectionResponse

@Database(entities = [GDirectionResponse::class], version = 7, exportSchema = false)
@TypeConverters(DataConvertor::class)
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun gDirectionDao(): GDirectionDao
}