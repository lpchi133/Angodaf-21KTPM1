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
            if(hotel != null) {
                listener(hotel)
            }
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }
    fun getHotelList(listener: (List<Hotel>) -> Unit) {
        val hotelList = mutableListOf<Hotel>()
        database.child("hotels").get().addOnSuccessListener { dataSnapshot ->
            for (hotelSnapshot in dataSnapshot.children) {
                val hotel = hotelSnapshot.getValue(Hotel::class.java)
                hotel?.let {
                    it.ID = hotelSnapshot.key ?: ""
                    hotelList.add(it)
                }
            }
            listener(hotelList)
        }.addOnFailureListener { exception ->
            Log.e("firebase", "Error getting hotel list", exception)
            listener(emptyList()) // Trả về danh sách rỗng nếu có lỗi xảy ra
        }
    }

}