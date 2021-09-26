package com.example.gdirectionspoc.pojo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Leg(
    @field:Json(name = "end_address") val endAddress: String,
    @field:Json(name = "end_location") val endLocation: Location,
    @field:Json(name = "start_address") val startAddress: String,
    @field:Json(name = "start_location") val startLocation: Location,
    @field:Json(name = "html_instructions") val htmlInstructions: String,
    @field:Json(name = "polyline") val polyline: OverviewPolyline,
    val steps: ArrayList<Leg>,
)
