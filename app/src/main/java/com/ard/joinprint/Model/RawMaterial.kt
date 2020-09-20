package com.ard.joinprint.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RawMaterial {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("uuid")
    @Expose
    var uuid: String? = null

    @SerializedName("sku")
    @Expose
    var sku: String? = null

    @SerializedName("nameEng")
    @Expose
    var nameEng: String? = null

    @SerializedName("nameChin")
    @Expose
    var nameChin: Any? = null

    @SerializedName("unit")
    @Expose
    var unit: String? = null

    @SerializedName("defaultPrice")
    @Expose
    var defaultPrice: Double? = null

    @SerializedName("packing")
    @Expose
    var packing: Any? = null

    @SerializedName("stores")
    @Expose
    var stores: List<Store>? = null

    @SerializedName("warehouses")
    @Expose
    var warehouses: List<Warehouse>? = null

    @SerializedName("categories")
    @Expose
    var categories: List<Categories>? = null

    @SerializedName("supplier")
    @Expose
    var supplier: Supplier? = null

    @SerializedName("active")
    @Expose
    var active: Int? = null

    @SerializedName("updatedBy")
    @Expose
    var updatedBy: UpdatedBy? = null

    @SerializedName("createdAt")
    @Expose
    var createdAt: String? = null

    @SerializedName("updatedAt")
    @Expose
    var updatedAt: String? = null
}