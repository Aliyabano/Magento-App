package com.aliyabano.vendorapp.Model


import com.google.gson.annotations.SerializedName

data class VendorMsg(
    @SerializedName("hashkey")
    val hashkey: String,
    @SerializedName("vendor")
    val vendor: List<Vendor>,
    @SerializedName("vendor_id")
    val vendorId: Int
) {
    data class Vendor(
        @SerializedName("key")
        val key: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("value")
        val value: Any
    )
}