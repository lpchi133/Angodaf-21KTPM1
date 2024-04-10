package com.example.angodafake.Utilities

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
        val hotelsQuery = database.child("hotels/$ID")
        hotelsQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Xử lý khi dữ liệu thay đổi
                val hotel = dataSnapshot.getValue(Hotel::class.java)
                listener(hotel!!)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })
    }
}