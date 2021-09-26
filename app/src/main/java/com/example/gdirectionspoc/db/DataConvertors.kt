package com.example.gdirectionspoc.db

import androidx.room.TypeConverter
import com.example.gdirectionspoc.pojo.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow


class DataConvertors {

    @TypeConverter
    fun toGeoCodedWayPoints(value: String): ArrayList<GeoCodedWayPoint> {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<GeoCodedWayPoint>>(){}.type

        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromGeoCodedWayPoints(values: ArrayList<GeoCodedWayPoint>): String {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<GeoCodedWayPoint>>(){}.type

        return gson.toJson(values, type)

    }

    @TypeConverter
    fun toRoutes(value: String): ArrayList<Route> {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Route>>(){}.type

        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromRoutes(values: ArrayList<Route>): String {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Route>>(){}.type

        return gson.toJson(values, type)

    }

    @TypeConverter
    fun toLocation(value: String): Location {
        val gson = Gson()
        val type = object : TypeToken<Location>(){}.type

        return gson.fromJson(value, type)


    }

    @TypeConverter
    fun fromLocation(value: Location): String {
        val gson = Gson()
        val type = object : TypeToken<Location>(){}.type

        return gson.toJson(value, type)

    }

    @TypeConverter
    fun toOverviewPolyline(value: String): OverviewPolyline {
        val gson = Gson()
        val type = object : TypeToken<OverviewPolyline>(){}.type

        return gson.fromJson(value, type)

    }

    @TypeConverter
    fun fromOverviewPolyline(value: OverviewPolyline): String {
        val gson = Gson()
        val type = object : TypeToken<OverviewPolyline>(){}.type

        return gson.toJson(value, type)

    }

    @TypeConverter
    fun toLegs(value: String): ArrayList<Leg> {
        val gson = Gson()
        val type = object : TypeToken<Leg>(){}.type

        return gson.fromJson(value, type)

    }

    @TypeConverter
    fun fromLegs(values: ArrayList<Leg>): String {
        val gson = Gson()
        val type = object : TypeToken<Leg>(){}.type

        return gson.toJson(values, type)

    }

    @TypeConverter
    fun toFlowGDirection(value: String): Flow<GDirectionResponse> {
        val gson = Gson()
        val type = object : TypeToken<Flow<GDirectionResponse>>(){}.type

        return gson.fromJson(value, type)

    }

    @TypeConverter
    fun fromFlowGDirection(values: Flow<GDirectionResponse>): String {
        val gson = Gson()
        val type = object : TypeToken<Flow<GDirectionResponse>>(){}.type

        return gson.toJson(values, type)

    }

    @TypeConverter
    fun toGDirectionResponse(value: String): GDirectionResponse {
        val gson = Gson()
        val type = object : TypeToken<GDirectionResponse>(){}.type

        return gson.fromJson(value, type)

    }

    @TypeConverter
    fun fromGDirectionResponse(values: GDirectionResponse): String {
        val gson = Gson()
        val type = object : TypeToken<GDirectionResponse>(){}.type

        return gson.toJson(values, type)

    }
}