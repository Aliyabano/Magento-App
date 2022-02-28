package com.aliyabano.vendorapp.ExtraModel

sealed class SealedModel {

    data class GeneralInformation(
        val fieldName: String?,
        val fieldToPost: String?,
        val inputType: String?,
        val isRequired: String?,
        val options: List<GeneralOption>,
        val savedValue: String?,
        val type: String?
    ) : SealedModel() {
        data class GeneralOption(
            val label: String?,
            val value: String?
        )
    }

//    data class AddressInformation(
//        val fieldName: String?,
//        val fieldToPost: String?,
//        val inputType: String?,
//        val isRequired: String?,
//        val options: List<AddressOption>,
//        val savedValue: String?,
//        val type: String?
//    ) : SealedModel() {
//        data class AddressOption(
//            val label: String?,
//            val value: String?
//        )
//    }
//
//    data class CompanyInformation(
//        val fieldName: String?,
//        val fieldToPost: String?,
//        val inputType: String?,
//        val isRequired: String?,
//        val savedValue: String?,
//        val type: String?
//    ) : SealedModel()
//
//    data class SEOInformation(
//        val fieldName: String?,
//        val fieldToPost: String?,
//        val inputType: String?,
//        val isRequired: String?,
//        val savedValue: String?,
//        val type: String?
//    ) : SealedModel()
//
//    data class SupportInformation(
//        val fieldName: String?,
//        val fieldToPost: String?,
//        val inputType: String?,
//        val isRequired: String?,
//        val savedValue: String?,
//        val type: String?
//    ) : SealedModel()

}