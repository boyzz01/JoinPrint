package com.ard.joinprint.Model.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TokenResponse {
    @SerializedName("token_type")
    @Expose
    var tokenType: String? = null

    @SerializedName("expires_in")
    @Expose
    var expiresIn: Int? = null

    @SerializedName("access_token")
    @Expose
    var accessToken: String? = null

    @SerializedName("refresh_token")
    @Expose
    var refreshToken: String? = null

    //errorResponse
    @SerializedName("error")
    @Expose
    var error: Boolean? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("class")
    @Expose
    var class_: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: Any? = null
}