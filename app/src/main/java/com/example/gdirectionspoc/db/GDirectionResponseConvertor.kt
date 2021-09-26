package com.example.gdirectionspoc.db

import androidx.room.TypeConverter
import com.example.gdirectionspoc.pojo.GDirectionResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GDirectionResponseConvertor {

    @TypeConverter
    fun toGDirectionArrayList(value: String): ArrayList<GDirectionResponse> {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<GDirectionResponse>>(){}.type

        return gson.fromJson(value, type)

    }

    @TypeConverter
    fun fromGDirectionArrayList(values: ArrayList<GDirectionResponse>): String {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<GDirectionResponse>>(){}.type

        return gson.toJson(values, type)

    }
}