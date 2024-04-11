package com.example.angodafake.Utilities

import android.util.Log
import com.example.angodafake.db.Bookmark
import com.example.angodafake.db.Hotel
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

object HotelUtils {
    private lateinit var database: DatabaseReference

    init {
        database = Firebase.database.reference
    }

    fun getHotelByID(ID: String, listener: (Hotel) -> Unit){
        database.child("hotels").child(ID).get().addOnSuccessListener {
            val hotel = it.getValue(Hotel::class.java)
            hotel?.ID = ID
            Log.d("hotelByID", "ID: $ID, hotel: ${hotel.toString()}")
            listener(hotel!!)
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
//        hotelsQuery.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // Xử lý khi dữ liệu thay đổi
//                val hotel = dataSnapshot.getValue(Hotel::class.java)
//                hotel?.ID = ID
//                Log.d("hotelByID", "ID: $ID, hotel: ${hotel.toString()}")
//                listener(hotel!!)
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Xử lý khi có lỗi xảy ra
//            }
//        })
    }
}