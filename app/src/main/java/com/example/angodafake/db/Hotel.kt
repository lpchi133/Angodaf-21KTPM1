package com.example.angodafake.db

import androidx.room.ColumnInfo
import androidx.room.Query
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Hotel(
    var ID: String? = null,
    var ID_Owner: String? = null,
    var name: String? = null,
    var phoneNumber: String? = null,
    var locationDetail: String? = null,
    var city: String? = null,
    var description: String? = null,
    var conveniences: String? = null,
    var highlight: String? = null,
    var star: Int? = 0,
    var point: Double? = 0.0,
    var profit: Double? = 0.0,
    var checkIn: String? = null,
    var checkOut: String? = null,
    var money: Int? = 0,
    var location: Double? = 0.0,
    var clean: Double? =  0.0,
    var service: Double? = 0.0,
    var convenience: Double? = 0.0,
    var total_comments: Int = 0) {
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