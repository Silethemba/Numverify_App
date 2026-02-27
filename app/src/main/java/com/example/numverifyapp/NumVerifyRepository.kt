package com.example.numverifyapp
import com.example.numverifyapp.Models.Country
import com.example.numverifyapp.Models.Telephone
import com.example.numverifyapp.RetrofitClient.retrofitClient
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

const val accessKey = "b089608159a93022fff45fbd58960de"

class NumVerifyRepository {

    fun getCountryCodeList(): Map<String, Country>? {
        return try {
            val countryCodeList: Call<Map<String, Country>?> =
                retrofitClient.getCountryList(accessKey = accessKey)
            val countryCodeResponse: Response<Map<String, Country>?> = countryCodeList.execute()

            if (countryCodeResponse.isSuccessful) {
                countryCodeResponse.body()
            } else {
                //Handle API error
                println("Error: ${countryCodeResponse.errorBody()?.string()}")
                null
            }
        } catch (e: IOException) {
            //Handle Network errors
            println("Error: ${e.message}")
            null
        } catch (e: Exception) {
            //Handle any other exceptions
            println("Error: ${e.message}")
            null
        }
    }

    fun validateNumber(number: String, countryCode: String): Telephone? {
        return try {
            val validateNumber: Call<Telephone?> =
                retrofitClient.validateNumber(number, countryCode, "1", accessKey = accessKey)
            val validateNumberResponse: Response<Telephone?> = validateNumber.execute()

            if (validateNumberResponse.isSuccessful) {
                validateNumberResponse.body()
            } else {
                //Handle API error
                println("Error: ${validateNumberResponse.errorBody()?.string()}")
                null
            }
        } catch (e: IOException) {
            //Handle Network errors
            println("Error: ${e.message}")
            null
        } catch (e: Exception) {
            //Handle any other exceptions
            println("Error: ${e.message}")
            null
        }
    }
}
