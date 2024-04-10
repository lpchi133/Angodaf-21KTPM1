package com.example.angodafake.db

import androidx.room.ColumnInfo
import androidx.room.Query
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Hotel(
    var ID: String? = null,
    val ID_Owner: String? = null,
    val name: String? = null,
    val locationDetail: String? = null,
    val city: String? = null,
    val description: String? = null,
    val conveniences: String? = null,
    val point: Float? = 0.0f,
    val profit: Double? = 0.0) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}

//@Query("Select * from hotel_db")
//fun getHotelList() : List<Hotel>
//@Query("SELECT * FROM hotel_db WHERE id = :hotel_id")
//fun getHotelByID(hotel_id: Int): Hotel
//@Query("SELECT * FROM hotel_db WHERE locationDetail = :hotel_location")
//fun getHotelByLocation(hotel_location: String): Hotel
//@Query("SELECT * FROM hotel_db WHERE name = :hotel_name")
//fun getHotelByName(hotel_name: String): Hotel