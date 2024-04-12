package com.example.angodafake.db

import androidx.room.ColumnInfo
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Purchase(
    val ID_Owner: String? = null,
    val ID_Hotel: String? = null,
    val ID_Room: String? = null,
    val time_booking: String? = null,
    val time_purchase: String? = null,
    val total_purchase: Double? = 0.0,
    val status_purchase: Boolean? = null,

    val detail: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}

