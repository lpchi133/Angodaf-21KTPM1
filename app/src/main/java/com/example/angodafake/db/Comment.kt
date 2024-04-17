package com.example.angodafake.db

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Comment(
    var ID: String? = null,
    var ID_Owner: String? = null,
    var ID_Hotel: String? = null,
    var time: String? = null,
    var point: Double? = 0.0,
    var content: String? = null,
    var money: Int? = 0,
    var location: Int? = 0,
    var clean: Int? =  0,
    var service: Int? = 0,
    var convenience: Int? = 0,
    var type_customer: String? = null,
    var title: String? = null,
    ) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}