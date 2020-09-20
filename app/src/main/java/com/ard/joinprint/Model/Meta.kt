package com.ard.joinprint.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meta {
    @SerializedName("current_page")
    @Expose
    var currentPage: Int? = null

    @SerializedName("from")
    @Expose
    var from: Int? = null

    @SerializedName("last_page")
    @Expose
    var lastPage: Int? = null

    @SerializedName("path")
    @Expose
    var path: String? = null

    @SerializedName("per_page")
    @Expose
    var perPage: Int? = null

    @SerializedName("to")
    @Expose
    var to: Int? = null

    @SerializedName("total")
    @Expose
    var total: Int? = null
}