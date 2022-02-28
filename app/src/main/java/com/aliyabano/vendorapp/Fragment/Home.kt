package com.aliyabano.vendorapp.Fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliyabano.vendorapp.ApiResponse
import com.aliyabano.vendorapp.ExtraModel.SealedModel
import com.aliyabano.vendorapp.R
import com.aliyabano.vendorapp.ViewModel.MainVM
import com.aliyabano.vendorapp.databinding.*
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.collect
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

@AndroidEntryPoint
class Home : Fragment() {

    val mainVM: MainVM by viewModels()
    val hashKey = "rNRccT7K6NVaZ6wgvsuLEUMow22IPYqp"

    lateinit var generalCard: CardItemBinding
    lateinit var generalTitle: HeaderTxtLytBinding
    lateinit var generalEdt: EdtLytBinding
    lateinit var generalSpinner: SpinnerLytBinding
    lateinit var generalImg: ImgLytBinding
    private var generalOption = mutableListOf<String>()
    private lateinit var generalEdtHeaderTitle : EdtTitleLytBinding

    lateinit var seoCard: CardItemBinding
    lateinit var seoTitle: HeaderTxtLytBinding
    lateinit var seoEdt: EdtLytBinding
    lateinit var seoSpinner: SpinnerLytBinding
    lateinit var seoImg: ImgLytBinding
    private var seoOption = mutableListOf<String>()
    //step 1
    private lateinit var seoEdtHeaderTitle : EdtTitleLytBinding

    lateinit var companyCard: CardItemBinding
    lateinit var companyTitle: HeaderTxtLytBinding
    lateinit var companyEdt: EdtLytBinding
    lateinit var companySpinner: SpinnerLytBinding
    lateinit var companyImg: ImgLytBinding
    private var companyOption = mutableListOf<String>()
    private lateinit var companyEdtHeaderTitle : EdtTitleLytBinding

    lateinit var addressCard: CardItemBinding
    lateinit var addressTitle: HeaderTxtLytBinding
    lateinit var addressEdt: EdtLytBinding
    lateinit var addressSpinner: SpinnerLytBinding
    lateinit var addressImg: ImgLytBinding
    private var addressOption = mutableListOf<String>()
    private lateinit var addressEdtHeaderTitle : EdtTitleLytBinding

    lateinit var supportCard: CardItemBinding
    lateinit var supportTitle: HeaderTxtLytBinding
    lateinit var supportEdt: EdtLytBinding
    lateinit var supportSpinner: SpinnerLytBinding
    lateinit var supportImg: ImgLytBinding
    private var supportOption = mutableListOf<String>()
    private lateinit var supportEdtHeaderTitle : EdtTitleLytBinding

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        generalCard = CardItemBinding.inflate(layoutInflater)
        generalTitle = HeaderTxtLytBinding.inflate(layoutInflater)


        seoCard = CardItemBinding.inflate(layoutInflater)
        seoTitle = HeaderTxtLytBinding.inflate(layoutInflater)

        companyCard = CardItemBinding.inflate(layoutInflater)
        companyTitle = HeaderTxtLytBinding.inflate(layoutInflater)

        addressCard = CardItemBinding.inflate(layoutInflater)
        addressTitle = HeaderTxtLytBinding.inflate(layoutInflater)

        supportCard = CardItemBinding.inflate(layoutInflater)
        supportTitle = HeaderTxtLytBinding.inflate(layoutInflater)


        binding = inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Adding Cards To Main Layout
        binding.dynamicLyt.addView(generalCard.root)
        binding.dynamicLyt.addView(seoCard.root)
        binding.dynamicLyt.addView(companyCard.root)
        binding.dynamicLyt.addView(addressCard.root)
        binding.dynamicLyt.addView(supportCard.root)


        //Setting General Information Title
        generalCard.cardLyt.addView(generalTitle.root)
        generalTitle.title.text = "General Information"

        //Seo Card Title
        seoCard.cardLyt.addView(seoTitle.root)
        seoTitle.title.text = "Seo Information"

        //Address Card Title
        addressCard.cardLyt.addView(addressTitle.root)
        addressTitle.title.text = "Address Information"

        //Company Card Title
        companyCard.cardLyt.addView(companyTitle.root)
        companyTitle.title.text = "Company Information"

        //Support Card title
        supportCard.cardLyt.addView(supportTitle.root)
        supportTitle.title.text ="Support Information"


        val id = 1
        getVendor(id)


        btnSave.setOnClickListener {

            val item = generalEdt.edtItem.text.toString()

            val vendor = JSONArray()
            val data = JSONObject()

            //Update General Info
            data.put("key", "name")
            data.put("value", "Test Test")
            data.put("type", "text")
            vendor.put(data)
            updateData(hashKey, id, vendor)

        }


    }

    private fun updateData(
        hashKey: String,
        id: Int,
        arraList: JSONArray,
    ) {
        val loader = ProgressDialog(context)
        loader.setMessage("Please wait")
        loader.setCanceledOnTouchOutside(false)
        lifecycleScope.launchWhenStarted {
            mainVM.updateVendorData(vendorKey = hashKey, id = id.toString(), arraList).collect {
                when (it) {
                    is ApiResponse.Failure -> {
                        Log.e("{UpdateData", "${it.msg}")
                        loader.dismiss()
                    }
                    is ApiResponse.Loading -> {
                        loader.show()
                    }
                    is ApiResponse.Success -> {
                        loader.dismiss()
                        Log.e("Update", "${it.data}")
                    }
                }
            }
        }
    }

    private fun getVendor(id: Int) {

        val loader = ProgressDialog(context)
        loader.setMessage("Please Wait....")
        loader.setCanceledOnTouchOutside(false)

        lifecycleScope.launchWhenStarted {
            mainVM.getVendor(id).collect {
                when (it) {
                    is ApiResponse.Failure -> {
                        loader.dismiss()
                        Log.e("{GetVendorError}", "${it.msg}")
                    }
                    ApiResponse.Loading -> {
                        loader.show()
                    }
                    is ApiResponse.Success -> {
                        loader.dismiss()

                        val item = it.data!!.data!!.vendorAttributes

                        for (generalLoop in item!!.generalInformation!!) {

                            when (generalLoop!!.type) {
                                "text" -> {
                                    //step 2 ------------------------------------------------------
                                    generalEdtHeaderTitle = EdtTitleLytBinding.inflate(layoutInflater)
                                    generalEdtHeaderTitle.textView.text ="${generalLoop.fieldName}"
                                    generalCard.cardLyt.addView(generalEdtHeaderTitle.root)
                                    // -------------------------------------------------------------
                                    generalEdt = EdtLytBinding.inflate(layoutInflater)
                                    generalCard.cardLyt.addView(generalEdt.root)
                                    generalEdt.edtItem.setText("${generalLoop.savedValue}")
                                }
                                "image" -> {
                                    //step 2 Repeat ------------------------------------------------------
                                    generalEdtHeaderTitle = EdtTitleLytBinding.inflate(layoutInflater)
                                    generalEdtHeaderTitle.textView.text ="${generalLoop.fieldName}"
                                    generalCard.cardLyt.addView(generalEdtHeaderTitle.root)
                                    // -------------------------------------------------------------

                                    generalImg = ImgLytBinding.inflate(layoutInflater)
                                    generalCard.cardLyt.addView(generalImg.root)
                                    Glide.with(this@Home).load(generalLoop.savedValue)
                                        .into(generalImg.imgLytImg)
                                }
                                "select" -> {
                                    //step 2 Repeat  ------------------------------------------------------
                                    generalEdtHeaderTitle = EdtTitleLytBinding.inflate(layoutInflater)
                                    generalEdtHeaderTitle.textView.text ="${generalLoop.fieldName}"
                                    generalCard.cardLyt.addView(generalEdtHeaderTitle.root)
                                    // -------------------------------------------------------------

                                    generalSpinner = SpinnerLytBinding.inflate(layoutInflater)
                                    generalCard.cardLyt.addView(generalSpinner.root)
                                    generalSpinner.spinner.hint = "${generalLoop.fieldName}"

                                    for (gOption in generalLoop.options!!) {
                                        generalOption.add(gOption!!.label!!)
                                    }
                                    val generalSAdapter = ArrayAdapter(
                                        requireContext(),
                                        R.layout.support_simple_spinner_dropdown_item,
                                        generalOption
                                    )
                                    generalSpinner.spinner.setAdapter(generalSAdapter)
                                }
                            }

                        }

                        for (companyLoop in item.companyInformation!!) {

                            //companySpinner = SpinnerLytBinding.inflate(layoutInflater)
                            when (companyLoop!!.type) {
                                "text" -> {

                                    companyEdtHeaderTitle = EdtTitleLytBinding.inflate(layoutInflater)
                                    companyEdtHeaderTitle.textView.text ="${companyLoop.fieldName}"
                                    companyCard.cardLyt.addView(companyEdtHeaderTitle.root)

                                    companyEdt = EdtLytBinding.inflate(layoutInflater)
                                    companyCard.cardLyt.addView(companyEdt.root)
                                    companyEdt.edtItem.setText("${companyLoop.savedValue}")
                                }
                                "image" -> {
                                    companyEdtHeaderTitle = EdtTitleLytBinding.inflate(layoutInflater)
                                    companyEdtHeaderTitle.textView.text ="${companyLoop.fieldName}"
                                    companyCard.cardLyt.addView(companyEdtHeaderTitle.root)

                                    companyImg = ImgLytBinding.inflate(layoutInflater)
                                    companyCard.cardLyt.addView(companyImg.root)
                                    Glide.with(this@Home).load(companyLoop.savedValue)
                                        .into(companyImg.imgLytImg)
                                }

                            }

                        }

                        for (addressLoop in item.addressInformation!!) {

                            when (addressLoop!!.type) {

                                "text" -> {

                                    addressEdtHeaderTitle = EdtTitleLytBinding.inflate(layoutInflater)
                                    addressEdtHeaderTitle.textView.text ="${addressLoop.fieldName}"
                                    addressCard.cardLyt.addView(addressEdtHeaderTitle.root)

                                    addressEdt = EdtLytBinding.inflate(layoutInflater)
                                    addressCard.cardLyt.addView(addressEdt.root)
                                    addressEdt.edtItem.setText("${addressLoop.savedValue}")
                                }

                                "image" -> {

                                    addressEdtHeaderTitle = EdtTitleLytBinding.inflate(layoutInflater)
                                    addressEdtHeaderTitle.textView.text ="${addressLoop.fieldName}"
                                    addressCard.cardLyt.addView(addressEdtHeaderTitle.root)

                                    addressImg = ImgLytBinding.inflate(layoutInflater)
                                    addressCard.cardLyt.addView(companyImg.root)
                                    Glide.with(this@Home).load(addressLoop.savedValue)
                                        .into(addressImg.imgLytImg)

                                }
                                "select" -> {
                                    addressEdtHeaderTitle = EdtTitleLytBinding.inflate(layoutInflater)
                                    addressEdtHeaderTitle.textView.text ="${addressLoop.fieldName}"
                                    addressCard.cardLyt.addView(addressEdtHeaderTitle.root)

                                    addressSpinner = SpinnerLytBinding.inflate(layoutInflater)
                                    addressCard.cardLyt.addView(addressSpinner.root)
                                    addressSpinner.spinner.hint ="${addressLoop.fieldName}"

                                    for (aOption in addressLoop.options!!) {
                                        addressOption.add(aOption!!.label!!)
                                    }
                                    val addressAdapter = ArrayAdapter(
                                        requireContext(),
                                        R.layout.support_simple_spinner_dropdown_item,
                                        addressOption
                                    )
                                    addressSpinner.spinner.setAdapter(addressAdapter)
                                }

                            }
                        }

                        for (seoLoop in item.sEOInformation!!) {
                            Log.e("SeoLoop","$seoLoop")
                            when (seoLoop!!.type) {
                                "textarea" -> {
                                    seoEdtHeaderTitle = EdtTitleLytBinding.inflate(layoutInflater)
                                    seoEdtHeaderTitle.textView.text ="${seoLoop.fieldName}"
                                    seoCard.cardLyt.addView(seoEdtHeaderTitle.root)

                                    seoEdt = EdtLytBinding.inflate(layoutInflater)
                                    seoCard.cardLyt.addView(seoEdt.root)
                                    seoEdt.edtItem.setText("${seoLoop.savedValue}")
                                }
                                "image" -> {
                                    seoEdtHeaderTitle = EdtTitleLytBinding.inflate(layoutInflater)
                                    seoEdtHeaderTitle.textView.text ="${seoLoop.fieldName}"
                                    seoCard.cardLyt.addView(seoEdtHeaderTitle.root)

                                    seoImg = ImgLytBinding.inflate(layoutInflater)
                                    seoCard.cardLyt.addView(seoImg.root)
                                    Glide.with(this@Home).load(seoLoop.savedValue)
                                        .into(seoImg.imgLytImg)
                                }

                            }

                        }

                        for (supportLoop in item.supportInformation!!){

                            when(supportLoop!!.type){

                                "text" ->{
                                    supportEdtHeaderTitle = EdtTitleLytBinding.inflate(layoutInflater)
                                    supportEdtHeaderTitle.textView.text ="${supportLoop.fieldName}"
                                    supportCard.cardLyt.addView(supportEdtHeaderTitle.root)

                                    supportEdt = EdtLytBinding.inflate(layoutInflater)
                                    supportCard.cardLyt.addView(supportEdt.root)
                                    supportEdt.edtItem.setText("${supportLoop.savedValue}")

                                }
                                "image" -> {
                                    supportEdtHeaderTitle = EdtTitleLytBinding.inflate(layoutInflater)
                                    supportEdtHeaderTitle.textView.text ="${supportLoop.fieldName}"
                                    supportCard.cardLyt.addView(supportEdtHeaderTitle.root)

                                    supportImg = ImgLytBinding.inflate(layoutInflater)
                                    supportCard.cardLyt.addView(supportImg.root)
                                    Glide.with(this@Home).load(supportLoop.savedValue)
                                        .into(supportImg.imgLytImg)
                                }
                            }

                        }

                    }
                    else -> {
                        Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }


}