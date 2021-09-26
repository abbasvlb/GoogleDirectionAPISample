package com.example.gdirectionspoc.pojo


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "g_direction_response_table")
@JsonClass(generateAdapter = true)
data class GDirectionResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @field:Json(name = "geocoded_waypoints") var geocodedWaypoints: ArrayList<GeoCodedWayPoint>,

    @field:Json(name = "routes") var routes: ArrayList<Route>,
    @field:Json(name = "status") var status: String
)
