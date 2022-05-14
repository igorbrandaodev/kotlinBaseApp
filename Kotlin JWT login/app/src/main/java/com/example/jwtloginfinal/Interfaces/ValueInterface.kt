package com.example.jwtloginfinal.Interfaces

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ValueInterface{
    @GET("values")
    fun getValues() : Observable<Response<List<String>>>

    @Headers("Content-Type:application/json")
    @GET("values/1")
    fun getValuesAuth(@Header("Authorization") token: String) : Observable<Response<List<String>>>
}
