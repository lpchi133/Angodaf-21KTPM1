package com.example.angodafake.db

import androidx.room.ColumnInfo
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Comment(
    val ID_Owner: String? = null,
    val ID_Hotel: String? = null,
    val time: String? = null,
    val point: Float? = 0.0f,
    val detail: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}