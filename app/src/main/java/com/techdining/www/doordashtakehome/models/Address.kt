package com.techdining.www.doordashtakehome.models

data class Address(
    val city: String,
    val lat: Double,
    val lng: Double,
    val printable_address: String,
    val state: String,
    val street: String
)