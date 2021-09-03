package com.example.gdirectionspoc.pojo

import com.google.gson.annotations.SerializedName
import java.time.Duration

data class Step(
//    @SerializedName("distance") val distance: HashMap<String, Long>,
//    @SerializedName("duration") val duration: HashMap<String, Long>,
    @SerializedName("end_address") val end_address: String,
    @SerializedName("end_location") val endLocation: Location,
    @SerializedName("start_address") val startAddress: String,
    @SerializedName("start_location") val startLocation: Location,
    @SerializedName("html_instructions") val htmlInstructions: String,
    @SerializedName("polyline") val polyline: OverviewPolyline,
)
