package com.ard.joinprint.Model.Response

import com.ard.joinprint.Model.Store
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListStoreResponse {
    @SerializedName("data")
    @Expose
    var data: List<Store>? = null


}