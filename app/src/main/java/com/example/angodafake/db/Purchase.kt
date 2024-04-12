package com.example.angodafake.db

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Purchase(
    var ID: String? = null,
    var ID_Owner: String? = null,
    var ID_Hotel: String? = null,
    var ID_Room: String? = null,
    var time_booking: String? = null,
    var time_purchase: String? = null,
    var total_purchase: Double? = 0.0,
    var status_purchase: String? = null,
    var time_cancel: String? = null,
    var quantity: Int? = 0,
    var detail: String? = null,
    var method: String? = null,) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}

