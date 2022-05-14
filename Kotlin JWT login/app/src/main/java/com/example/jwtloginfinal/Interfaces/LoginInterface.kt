package com.example.jwtloginfinal.Interfaces

import com.example.jwtloginfinal.Model.AccesCredentials
import com.example.jwtloginfinal.Model.TokenResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface LoginInterface {
    @Headers("Content-Type:application/json")
    @POST("login")
    fun getToken(@Body credentials: AccesCredentials) : Observable<Response<TokenResponse>>
}
