package com.example.angodafake.db

import androidx.room.ColumnInfo
import androidx.room.Query
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Picture(
    val ID_Hotel: String? = null,
    val picture: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}

//@Query("Select * from picture_db")
//fun getPictureList() : List<Picture>
//@Query("SELECT * FROM picture_db WHERE ID_Hotel = :hotel_id")
//fun getPictureByHotelID(hotel_id: Int): Picture