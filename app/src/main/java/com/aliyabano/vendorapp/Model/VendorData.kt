package com.aliyabano.vendorapp.Model


import com.google.gson.annotations.SerializedName
data class VendorData(
    @SerializedName("key")
    var key: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("value")
    var value: String
)
