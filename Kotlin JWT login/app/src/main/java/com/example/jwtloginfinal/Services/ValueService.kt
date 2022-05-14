package com.example.jwtloginfinal.Services

import com.example.jwtloginfinal.Interfaces.LoginInterface
import com.example.jwtloginfinal.Interfaces.ValueInterface
import com.example.jwtloginfinal.Model.AccesCredentials
import com.example.jwtloginfinal.Model.TokenResponse
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ValueService {

    private val myAppInterface: ValueInterface

    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val clientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        clientBuilder.addInterceptor(loggingInterceptor)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        myAppInterface = retrofit.create(ValueInterface::class.java)

    }

    companion object {
        private val BASE_URL = "http://apiadvonline.azurewebsites.net/api/"
        private var loginService: ValueService? = null
        /**
         * Gets my app client.
         *
         * @return the my app client
         */
        val instance: ValueService get() {
            if (loginService == null) {
                loginService = ValueService()
            }
            return loginService as ValueService
        }
    }

    fun getValues(): Observable<Response<List<String>>> {
        return myAppInterface.getValues()
    }

    fun getValuesAuth(token: String): Observable<Response<List<String>>> {
        return myAppInterface.getValuesAuth(token)
    }

}