package com.felpster.userslist.data.model

import com.squareup.moshi.Json

data class Geo(
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lng")
    val lng: Double,
)
