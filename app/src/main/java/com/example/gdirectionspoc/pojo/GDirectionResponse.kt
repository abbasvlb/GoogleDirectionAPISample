package com.example.gdirectionspoc.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "g_direction_table")
data class GDirectionResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("geocoded_waypoints") var geocodedWaypoints: ArrayList<GeoCodedWayPoint>,
    @SerializedName("routes") var routes: ArrayList<Route>,
    @SerializedName("status") var status: String
)
