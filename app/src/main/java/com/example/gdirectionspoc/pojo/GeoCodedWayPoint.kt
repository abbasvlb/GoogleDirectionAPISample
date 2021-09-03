package com.example.gdirectionspoc.pojo

import com.google.gson.annotations.SerializedName

data class GeoCodedWayPoint(
    @SerializedName("geocoder_status") val geocoderStatus: String,
    @SerializedName("place_id") val placeId: String,
    @SerializedName("types") val types: ArrayList<String>

)
