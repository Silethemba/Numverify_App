package com.example.numverifyapp
import com.google.gson.GsonBuilder
import com.google.gson.Strictness
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient{

        const val BASE_URL = "https://apilayer.net/api/"
        private val logging = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY) // Set the desired log level

        private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging) // Add logging as an application interceptor
            // Add other network interceptors or timeouts here
            .build()
        private val gson = GsonBuilder()
            .setStrictness(Strictness.LENIENT)
            .create()

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    val retrofitClient = retrofit.create<NumverifyApi>()

}


