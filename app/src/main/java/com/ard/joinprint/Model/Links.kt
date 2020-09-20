package com.ard.joinprint.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Links {
    @SerializedName("first")
    @Expose
    var first: String? = null

    @SerializedName("last")
    @Expose
    var last: String? = null

    @SerializedName("prev")
    @Expose
    var prev: Any? = null

    @SerializedName("next")
    @Expose
    var next: String? = null
}