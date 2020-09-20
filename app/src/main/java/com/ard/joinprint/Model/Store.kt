package com.ard.joinprint.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Store {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("uuid")
    @Expose
    var uuid: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("cuisine")
    @Expose
    var cuisine: String? = null

    @SerializedName("order_prefix")
    @Expose
    var orderPrefix: String? = null

    @SerializedName("isOnline")
    @Expose
    var isOnline: Boolean? = null

    @SerializedName("previewUrl")
    @Expose
    var previewUrl: Any? = null

    @SerializedName("authCode")
    @Expose
    var authCode: String? = null

    @SerializedName("warehouse")
    @Expose
    var warehouse: Warehouse? = null

    @SerializedName("tenant")
    @Expose
    var tenant: Tenant? = null
}