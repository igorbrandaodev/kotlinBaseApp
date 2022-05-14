package com.example.jwtloginfinal.Services


import com.example.jwtloginfinal.Model.AccesCredentials
import com.example.jwtloginfinal.Model.TokenResponse
import com.example.jwtloginfinal.Interfaces.LoginInterface
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginService {

    private val myAppInterface: LoginInterface

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

        myAppInterface = retrofit.create(LoginInterface::class.java)

    }

    companion object {
        private val BASE_URL = "http://apiadvonline.azurewebsites.net/api/"
        private var loginService: LoginService? = null
        /**
         * Gets my app client.
         *
         * @return the my app client
         */
        val instance: LoginService get() {
            if (loginService == null) {
                loginService = LoginService()
            }
            return loginService as LoginService
        }
    }

    fun getToken(credentials: AccesCredentials): Observable<Response<TokenResponse>> {
        return myAppInterface.getToken(credentials)
    }

}