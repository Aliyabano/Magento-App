package com.aliyabano.vendorapp.Model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class VendorResponse(
    @SerializedName("data")
    val `data`: Data?
) {
    @Keep
    data class Data(
        @SerializedName("success")
        val success: Boolean?,
        @SerializedName("vendor_attributes")
        val vendorAttributes: VendorAttributes?
    ) {
        @Keep
        data class VendorAttributes(
            @SerializedName("Address Information")
            val addressInformation: List<AddressInformation?>?,
            @SerializedName("Company Information")
            val companyInformation: List<CompanyInformation?>?,
            @SerializedName("General Information")
            val generalInformation: List<GeneralInformation?>?,
            @SerializedName("SEO Information")
            val sEOInformation: List<SEOInformation?>?,
            @SerializedName("Support Information")
            val supportInformation: List<SupportInformation?>?
        ) {
            @Keep
            data class AddressInformation(
                @SerializedName("field_name")
                val fieldName: String?,
                @SerializedName("field_to_post")
                val fieldToPost: String?,
                @SerializedName("input_type")
                val inputType: String?,
                @SerializedName("is_required")
                val isRequired: String?,
                @SerializedName("options")
                val options: List<Option?>?,
                @SerializedName("saved_value")
                val savedValue: String?,
                @SerializedName("type")
                val type: String?
            ) {
                @Keep
                data class Option(
                    @SerializedName("label")
                    val label: String?,
                    @SerializedName("value")
                    val value: String?
                )
            }

            @Keep
            data class CompanyInformation(
                @SerializedName("field_name")
                val fieldName: String?,
                @SerializedName("field_to_post")
                val fieldToPost: String?,
                @SerializedName("input_type")
                val inputType: String?,
                @SerializedName("is_required")
                val isRequired: String?,
                @SerializedName("saved_value")
                val savedValue: String?,
                @SerializedName("type")
                val type: String?
            )

            @Keep
            data class GeneralInformation(
                @SerializedName("field_name")
                val fieldName: String?,
                @SerializedName("field_to_post")
                val fieldToPost: String?,
                @SerializedName("input_type")
                val inputType: String?,
                @SerializedName("is_required")
                val isRequired: String?,
                @SerializedName("options")
                val options: List<Option?>?,
                @SerializedName("saved_value")
                val savedValue: String?,
                @SerializedName("type")
                val type: String?
            ) {
                @Keep
                data class Option(
                    @SerializedName("label")
                    val label: String?,
                    @SerializedName("value")
                    val value: Any?
                )
            }

            @Keep
            data class SEOInformation(
                @SerializedName("field_name")
                val fieldName: String?,
                @SerializedName("field_to_post")
                val fieldToPost: String?,
                @SerializedName("input_type")
                val inputType: String?,
                @SerializedName("is_required")
                val isRequired: String?,
                @SerializedName("saved_value")
                val savedValue: String?,
                @SerializedName("type")
                val type: String?
            )

            @Keep
            data class SupportInformation(
                @SerializedName("field_name")
                val fieldName: String?,
                @SerializedName("field_to_post")
                val fieldToPost: String?,
                @SerializedName("input_type")
                val inputType: String?,
                @SerializedName("is_required")
                val isRequired: String?,
                @SerializedName("saved_value")
                val savedValue: String?,
                @SerializedName("type")
                val type: String?
            )
        }
    }
}