package com.example.angodafake.db

import androidx.room.ColumnInfo
import androidx.room.Query
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Rooms(
    val ID_Hotel: String? = null,
    val quantity: Int? = 0,
    val available: Int? = 0,
    val type: String? = null,
    val acreage: Double? = 0.0,
    val direction: String? = null,
    val benefit: String? = null,
    val price: Double? = 0.0,
    val bedQuantity: Int? = 0,
    val picture: String? = null,
    ) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}

//@Query("Select * from room_db")
//fun getRoomList() : List<Rooms>
//@Query("SELECT * FROM room_db WHERE ID_Hotel = :hotel_id")
//fun getRoomsByHotelID(hotel_id: Int): List<Rooms>

