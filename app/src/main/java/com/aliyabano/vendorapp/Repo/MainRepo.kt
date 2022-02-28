package com.aliyabano.vendorapp.Repo

import android.util.Log
import com.aliyabano.vendorapp.Model.VendorData
import com.example.vendorapp.Network.ApiServices
import com.aliyabano.vendorapp.result
import org.json.JSONArray
import javax.inject.Inject

class MainRepo @Inject constructor(val apiServices: ApiServices) {


    fun getVendorDetails(id: Int) = result {
        apiServices.vendorData(id)
    }

    fun updateVendorData(vendorKey: String, id: String, vendor:JSONArray) =
        result { apiServices.updateVendorData(hashkey = vendorKey, id = id, vendor)
        }
}