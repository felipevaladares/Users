package com.felpster.userslist.data.model

import com.squareup.moshi.Json

data class Address (
	@Json(name = "street")
	val street : String,

	@Json(name = "suite")
	val suite : String,

	@Json(name = "city")
	val city : String,

	@Json(name = "zipcode")
	val zipcode : String,

	@Json(name = "geo")
	val geo : Geo,
)