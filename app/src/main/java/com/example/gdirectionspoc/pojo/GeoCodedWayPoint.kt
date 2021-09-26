package com.example.gdirectionspoc.pojo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeoCodedWayPoint(
    @field:Json(name = "geocoder_status") val geocoderStatus: String,
    @field:Json(name = "place_id") val placeId: String,
    @field:Json(name = "types") val types: ArrayList<String>
)
