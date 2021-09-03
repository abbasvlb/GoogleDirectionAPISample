package com.example.gdirectionspoc.pojo

import com.google.gson.annotations.SerializedName

data class GDirectionResponse(
    @SerializedName("geocoded_waypoints") var geocodedWaypoints: ArrayList<GeoCodedWayPoint>,
    @SerializedName("routes") var routes: ArrayList<Route>,
    @SerializedName("status") var status: String
)
