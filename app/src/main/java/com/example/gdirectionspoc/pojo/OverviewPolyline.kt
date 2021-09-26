package com.example.gdirectionspoc.pojo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OverviewPolyline(
    @field:Json(name = "points") val points: String
)
