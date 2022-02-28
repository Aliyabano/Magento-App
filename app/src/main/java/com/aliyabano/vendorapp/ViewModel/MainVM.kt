package com.aliyabano.vendorapp.ViewModel

import androidx.lifecycle.ViewModel
import com.aliyabano.vendorapp.Model.VendorData
import com.aliyabano.vendorapp.Repo.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONArray
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(val mainRepo: MainRepo) : ViewModel() {

    fun getVendor(id:Int) = mainRepo.getVendorDetails(id)

    fun updateVendorData(
        vendorKey:String,
        id:String, vendor : JSONArray
    ) =
        mainRepo.updateVendorData(vendorKey = vendorKey, id = id, vendor)
}