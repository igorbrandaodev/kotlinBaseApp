package com.example.jwtloginfinal.Model

import com.google.gson.annotations.SerializedName

class TokenResponse {
    @SerializedName("accessToken")
    var accessToken : String= ""
    @SerializedName("refreshToken")
    var refreshToken : String= ""
    @SerializedName("autenticated")
    var autenticated : String= ""
    @SerializedName("created")
    var created : String= ""
    @SerializedName("expiration")
    var expiration : String= ""
    @SerializedName("message")
    var message : String= ""
}