package com.example.numverifyapp

import com.example.numverifyapp.Models.Country
import com.example.numverifyapp.RetrofitClient.retrofitClient
import retrofit2.Call
import retrofit2.Response

const val accessKey = "6bf82b0f9330896fb1e9393f61e0bc6c"
class NumVerifyRepository {

    fun getCountryCodeList(): Map<String, Country>? {
        val countryCodeList: Call<Map<String, Country>?> =
            retrofitClient.getCountryList(accessKey = accessKey)
        val countryCodeResponse: Response<Map<String, Country>?> = countryCodeList.execute()
        return countryCodeResponse.body()
    }

    fun validateNumber(number: String, countryCode: String): Number? {
        val validateNumber: Call<Number?> =
            retrofitClient.validateNumber(number, countryCode, "1", accessKey = accessKey)
        val validateNumberResponse: Response<Number?> = validateNumber.execute()
        return validateNumberResponse.body()
    }
}
