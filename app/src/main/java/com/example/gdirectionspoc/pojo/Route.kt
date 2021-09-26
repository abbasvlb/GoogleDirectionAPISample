package com.example.gdirectionspoc.pojo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Route(
    @field:Json(name = "copyrights") val copyrights: String,
    @field:Json(name = "legs") val legs: ArrayList<Leg>,
    @field:Json(name = "overview_polyline") val overviewPolyline: OverviewPolyline,
    @field:Json(name = "summary") val summary: String,
    @field:Json(name = "waypoint_order") val waypointOrder: ArrayList<Int>

)
