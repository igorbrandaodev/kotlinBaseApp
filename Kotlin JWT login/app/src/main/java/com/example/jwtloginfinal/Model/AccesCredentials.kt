package com.example.jwtloginfinal.Model

import com.google.gson.annotations.SerializedName

class AccesCredentials {

    @SerializedName("userID")
    var userID : String = ""
    @SerializedName("accessKey")
    var accessKey : String = ""
    @SerializedName("refreshToken")
    var refreshToken : String = ""
    @SerializedName("grantType")
    var grantType : String = ""

}