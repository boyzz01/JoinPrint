package com.ard.joinprint.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Supplier {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("uuid")
    @Expose
    var uuid: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("tel")
    @Expose
    var tel: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("remark")
    @Expose
    var remark: Any? = null

    @SerializedName("contactName")
    @Expose
    var contactName: String? = null

    @SerializedName("createdAt")
    @Expose
    var createdAt: String? = null

    @SerializedName("updatedAt")
    @Expose
    var updatedAt: String? = null
}