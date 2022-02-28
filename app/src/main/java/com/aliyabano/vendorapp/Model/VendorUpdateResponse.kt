package com.aliyabano.vendorapp.Model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class VendorUpdateResponse(
    @SerializedName("data")
    val `data`: Data?
) {
    @Keep
    data class Data(
        @SerializedName("message")
        val message: String?,
        @SerializedName("profile_complete")
        val profileComplete: Int?,
        @SerializedName("success")
        val success: Boolean?,
        @SerializedName("valerts")
        val valerts: Int?
    )
}