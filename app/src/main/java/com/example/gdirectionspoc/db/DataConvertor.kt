package com.example.gdirectionspoc.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.gdirectionspoc.pojo.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.flow.Flow
import java.lang.reflect.Type
import javax.inject.Inject

@ProvidedTypeConverter
class DataConvertor(private val moshi: Moshi) {

    @TypeConverter
    fun toGeoCodedWayPoints(value: String): ArrayList<GeoCodedWayPoint> {
        return moshi.adapter<ArrayList<GeoCodedWayPoint>>(
            Types.newParameterizedType(
                ArrayList::class.java,
                GeoCodedWayPoint::class.java
            )
        ).fromJson(value)!!
    }

    @TypeConverter
    fun fromGeoCodedWayPoints(values: ArrayList<GeoCodedWayPoint>): String {
        return moshi.adapter<ArrayList<GeoCodedWayPoint>>(
            Types.newParameterizedType(
                ArrayList::class.java,
                GeoCodedWayPoint::class.java
            )
        ).toJson(values)!!
    }

    @TypeConverter
    fun toRoutes(value: String): ArrayList<Route> {

        return moshi.adapter<ArrayList<Route>>(
            Types.newParameterizedType(
                ArrayList::class.java,
                Route::class.java
            )
        ).fromJson(value)!!


    }

    @TypeConverter
    fun fromRoutes(values: ArrayList<Route>): String {

        return moshi.adapter<ArrayList<Route>>(
            Types.newParameterizedType(
                ArrayList::class.java,
                Route::class.java
            )
        ).toJson(values)!!
    }

    @TypeConverter
    fun toLocation(value: String): Location {

        return moshi.adapter<Location>(
            Types.newParameterizedType(
                ArrayList::class.java,
                Location::class.java
            )
        ).fromJson(value)!!
    }

    @TypeConverter
    fun toLocation(value: Location): String {
        return moshi.adapter<Location>(
            Types.newParameterizedType(
                ArrayList::class.java,
                Location::class.java
            )
        ).toJson(value)!!
    }

    @TypeConverter
    fun toOverviewPolyline(value: String): OverviewPolyline {
        return moshi.adapter<OverviewPolyline>(
            Types.newParameterizedType(
                ArrayList::class.java,
                OverviewPolyline::class.java
            )
        ).fromJson(value)!!
    }

    @TypeConverter
    fun fromOverviewPolyline(value: OverviewPolyline): String {
        return moshi.adapter<OverviewPolyline>(
            Types.newParameterizedType(
                ArrayList::class.java,
                OverviewPolyline::class.java
            )
        ).toJson(value)!!
    }

    @TypeConverter
    fun toLegs(value: String): ArrayList<Leg> {
        return moshi.adapter<ArrayList<Leg>>(
            Types.newParameterizedType(
                ArrayList::class.java,
                Leg::class.java
            )
        ).fromJson(value)!!
    }

    @TypeConverter
    fun fromLegs(values: ArrayList<Leg>): String {
        return moshi.adapter<ArrayList<Leg>>(
            Types.newParameterizedType(
                ArrayList::class.java,
                Leg::class.java
            )
        ).toJson(values)!!
    }

    @TypeConverter
    fun toFlowGDirection(value: String): Flow<GDirectionResponse> {
        return moshi.adapter<Flow<GDirectionResponse>>(
            Types.newParameterizedType(
                ArrayList::class.java,
                GDirectionResponse::class.java
            )
        ).fromJson(value)!!
    }

    @TypeConverter
    fun fromFlowGDirection(values: Flow<GDirectionResponse>): String {
        return moshi.adapter<Flow<GDirectionResponse>>(
            Types.newParameterizedType(
                ArrayList::class.java,
                GDirectionResponse::class.java
            )
        ).toJson(values)!!
    }

    @TypeConverter
    fun toGDirectionArrayList(value: String): ArrayList<GDirectionResponse?> {
        return moshi.adapter<ArrayList<GDirectionResponse?>>(
            Types.newParameterizedType(
                ArrayList::class.java,
                GDirectionResponse::class.java
            )
        ).fromJson(value)!!
    }

    @TypeConverter
    fun fromGDirectionArrayList(values: ArrayList<GDirectionResponse?>): String {
        return moshi.adapter<ArrayList<GDirectionResponse?>>(
            Types.newParameterizedType(
                ArrayList::class.java,
                GDirectionResponse::class.java
            )
        ).toJson(values)!!
    }

    @TypeConverter
    fun toGDirectionResponse(value: String): GDirectionResponse {
        return moshi.adapter<GDirectionResponse>(
            Types.newParameterizedType(
                ArrayList::class.java,
                GDirectionResponse::class.java
            )
        ).fromJson(value)!!
    }

    @TypeConverter
    fun fromGDirectionResponse(values: GDirectionResponse): String {
        return moshi.adapter<GDirectionResponse>(
            Types.newParameterizedType(
                ArrayList::class.java,
                GDirectionResponse::class.java
            )
        ).toJson(values)!!
    }

}