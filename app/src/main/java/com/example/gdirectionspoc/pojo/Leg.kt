package com.example.gdirectionspoc.pojo

import com.google.gson.annotations.SerializedName

data class Leg(
    @SerializedName("end_address") val end_address: String,
    @SerializedName("end_location") val endLocation: Location,
    @SerializedName("start_address") val startAddress: String,
    @SerializedName("start_location") val startLocation: Location,
    @SerializedName("html_instructions") val htmlInstructions: String,
    @SerializedName("polyline") val polyline: OverviewPolyline,
    @SerializedName("steps")val steps: ArrayList<Leg>,
)
