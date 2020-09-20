package com.ard.joinprint.Model.Response

import com.ard.joinprint.Model.RawMaterial
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MaterialDetailResponse {
    @SerializedName("data")
    @Expose
    var data: RawMaterial? = null
}