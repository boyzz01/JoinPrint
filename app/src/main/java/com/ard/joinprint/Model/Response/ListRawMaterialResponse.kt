package com.ard.joinprint.Model.Response

import com.ard.joinprint.Model.Links
import com.ard.joinprint.Model.Meta
import com.ard.joinprint.Model.RawMaterial
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListRawMaterialResponse {
    @SerializedName("data")
    @Expose
    var data: List<RawMaterial>? = null

    @SerializedName("links")
    @Expose
    var links: Links? = null

    @SerializedName("meta")
    @Expose
    var meta: Meta? = null
}