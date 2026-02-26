package com.example.numverifyapp.Models

data class Country(
    val country_name: String,
    val dialling_code: String,
)
data class Telephone(
    val carrier: String,
    val country_code: String,
    val country_name: String,
    val country_prefix: String,
    val international_format: String,
    val line_type: String,
    val local_format: String,
    val location: String,
    val number: String,
    val valid: Boolean
)