package com.example.vendorapp.Network

import com.aliyabano.vendorapp.Model.VendorData
import com.aliyabano.vendorapp.Model.VendorResponse
import com.aliyabano.vendorapp.Model.VendorUpdateResponse
import com.google.gson.JsonArray
import org.json.JSONArray
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {
    companion object {
        const val BASE_URL =
            "https://demo2.cedcommerce.com/magento/dev/marketplace_plat/pub/vendorapi/index/"
    }

    @POST("getProfileFields")
    suspend fun vendorData(@Query("vendor_id") id: Int): Response<VendorResponse>

    @FormUrlEncoded
    @POST("update")
    suspend fun updateVendorData(
        @Field(" hashkey") hashkey: String,
        @Field("vendor_id") id: String,
        @Field("vendor") vendor :JSONArray
    ): Response<VendorUpdateResponse>

    //@PartMap map: HashMap<String?, RequestBody?>
}