package com.example.numverifyapp

import com.example.numverifyapp.Models.Country
import com.example.numverifyapp.Models.Telephone
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NumverifyApi {
    @GET("countries")
    fun getCountryList(@Query("access_key")accessKey: String?): Call<Map<String, Country>?>

    @GET("validate")
    fun validateNumber(@Query("number") telNumber: String,
                       @Query("country_code") countryCode: String,
                       @Query("format") format: String?,
                       @Query("access_key")accessKey: String?): Call<Telephone?>
}