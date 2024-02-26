package com.felpster.userslist.data.model

import com.squareup.moshi.Json

data class Company(
    @Json(name = "name")
    val name: String,
    @Json(name = "catchPhrase")
    val catchPhrase: String,
    @Json(name = "bs")
    val bs: String,
)
