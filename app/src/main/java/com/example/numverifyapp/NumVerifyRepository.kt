package com.example.numverifyapp

import com.example.numverifyapp.Models.Country
import com.example.numverifyapp.Models.Telephone
import com.example.numverifyapp.RetrofitClient.retrofitClient
import retrofit2.Call
import retrofit2.Response

const val accessKey = "b089608159a93022fff45fbd58960dee"
class NumVerifyRepository {

    fun getCountryCodeList(): Map<String, Country>? {
        val countryCodeList: Call<Map<String, Country>?> =
            retrofitClient.getCountryList(accessKey = accessKey)
        val countryCodeResponse: Response<Map<String, Country>?> = countryCodeList.execute()
        return countryCodeResponse.body()
    }

    fun validateNumber(number: String, countryCode: String): Telephone? {
        val validateNumber: Call<Telephone?> =
            retrofitClient.validateNumber(number, countryCode, "1", accessKey = accessKey)
        val validateNumberResponse: Response<Telephone?> = validateNumber.execute()
        return validateNumberResponse.body()
    }
}
