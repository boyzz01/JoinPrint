package com.ard.joinprint.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UpdatedBy {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("uuid")
    @Expose
    var uuid: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("tel")
    @Expose
    var tel: Any? = null

    @SerializedName("hkid")
    @Expose
    var hkid: Any? = null

    @SerializedName("monthlySalary")
    @Expose
    var monthlySalary: Any? = null

    @SerializedName("hourlyWage")
    @Expose
    var hourlyWage: Any? = null

    @SerializedName("active")
    @Expose
    var active: Int? = null
}