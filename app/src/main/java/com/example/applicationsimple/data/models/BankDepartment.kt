package com.example.applicationsimple.data.models

data class BankDepartment(
    val USDIn: Float,
    val USDOut: Float,
    val EURIn: Float,
    val EUROut: Float,
    val RUBIn: Float,
    val RUBOut: Float,
    val streetType: String,
    val street: String,
    val homeNumber: String,
)
fun BankResponse.toBankDepartment(): BankDepartment {
    return BankDepartment(
        USDIn!!.toFloat(),
        USDOut!!.toFloat(),
        EURIn!!.toFloat(),
        EUROut!!.toFloat(),
        RUBIn!!.toFloat(),
        RUBOut!!.toFloat(),
        streetType!!,
        street!!,
        homeNumber!!
    )
}
