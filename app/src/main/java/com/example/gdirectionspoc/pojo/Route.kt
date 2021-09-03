package com.example.gdirectionspoc.pojo

import com.google.gson.annotations.SerializedName

data class Route(
    @SerializedName("copyrights") val copyrights: String,
    @SerializedName("legs") val legs: ArrayList<Leg>,
    @SerializedName("overview_polyline") val overviewPolyline: OverviewPolyline,
    @SerializedName("summary") val summary: String,
    @SerializedName("waypoint_order") val waypointOrder: ArrayList<Int>

)
