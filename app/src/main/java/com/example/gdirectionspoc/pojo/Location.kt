package com.example.gdirectionspoc.pojo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @field:Json(name = "lat") val lat: Double,
    @field:Json(name = "lng") val lang: Double,
)
