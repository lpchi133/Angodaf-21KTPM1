package com.example.angodafake.db

data class Voucher(
    var ID: String? = null,
    var ID_Hotel: String? = null,
    var value: Double? = 0.0,
    var exp_time: String? = null,
    var limit_price: Int? = 0,
    var percentage: Int? = 0,
    var quantity: Int? = 0,
){}
