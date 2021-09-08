package com.example.gdirectionspoc.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.gdirectionspoc.pojo.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import java.lang.reflect.Type

class DataConvertor {

    @TypeConverter
    fun toGeoCodedWayPoints(value: String): ArrayList<GeoCodedWayPoint> {
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<GeoCodedWayPoint>>() {}.type

        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromGeoCodedWayPoints(values: ArrayList<GeoCodedWayPoint>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<String>() {}.type

        return gson.toJson(values, type)
    }

    @TypeConverter
    fun toRoutes(value: String): ArrayList<Route>{
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Route>>(){}.type

        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromRoutes(values: ArrayList<Route>) : String{
        val gson = Gson()
        val type: Type = object : TypeToken<String>(){}.type

        return gson.toJson(values, type)
    }

    @TypeConverter
    fun toLocation(value: String) : Location{
        val gson = Gson()

        val type: Type = object : TypeToken<Location>(){}.type

        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun toLocation(value: Location) : String{
        val gson = Gson()

        val type: Type = object : TypeToken<String>(){}.type

        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toOverviewPolyline(value: String) : OverviewPolyline {
        val gson = Gson()

        val type: Type = object : TypeToken<OverviewPolyline>(){}.type

        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromOverviewPolyline(value: OverviewPolyline) : String{
        val gson = Gson()

        val type: Type = object : TypeToken<String>(){}.type

        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toLegs(value: String): ArrayList<Leg>{
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Leg>>(){}.type

        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromLegs(values: ArrayList<Leg>) : String{
        val gson = Gson()
        val type: Type = object : TypeToken<String>(){}.type

        return gson.toJson(values, type)
    }

    @TypeConverter
    fun toFlowGDirection(value: String): Flow<GDirectionResponse>{
        val gson = Gson()
        val type: Type = object : TypeToken<Flow<GDirectionResponse>>(){}.type

        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromFlowGDirection(values: Flow<GDirectionResponse>) : String{
        val gson = Gson()
        val type: Type = object : TypeToken<String>(){}.type

        return gson.toJson(values, type)
    }

    @TypeConverter
    fun toGDirectionArrayList(value: String): ArrayList<GDirectionResponse?>{
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<GDirectionResponse?>>(){}.type

        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromGDirectionArrayList(values: ArrayList<GDirectionResponse?>) : String{
        val gson = Gson()
        val type: Type = object : TypeToken<String>(){}.type

        return gson.toJson(values, type)
    }

    @TypeConverter
    fun toGDirectionResponse(value: String): GDirectionResponse{
        val gson = Gson()
        val type: Type = object : TypeToken<GDirectionResponse>(){}.type

        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromGDirectionResponse(values: GDirectionResponse) : String{
        val gson = Gson()
        val type: Type = object : TypeToken<String>(){}.type

        return gson.toJson(values, type)
    }

}