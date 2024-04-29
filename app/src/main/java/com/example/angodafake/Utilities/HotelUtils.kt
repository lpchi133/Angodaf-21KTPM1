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
    fun getHotelByOwnerID(ownerID: String, listener: (MutableList<Hotel>) -> Unit) {
        val hotelsQuery = database.child("hotels")

        hotelsQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val hotelsList = mutableListOf<Hotel>()
                for (hotelSnapshot in dataSnapshot.children) {
                    val hotel = hotelSnapshot.getValue(Hotel::class.java)
                    if (hotel?.ID_Owner == ownerID) {
                        hotel.ID = hotelSnapshot.key
                        hotel.let { hotelsList.add(it) }
                    }
                }
                listener(hotelsList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    fun updateRating(hotelID: String, point: Double, money_rating: Double, location: Double, clean: Double, service: Double, convenience: Double, callback: (String) -> Unit) {
        val ratingUpdate = hashMapOf<String, Any>(
            "/hotels/$hotelID/point" to point,
            "/hotels/$hotelID/money_rating" to money_rating,
            "/hotels/$hotelID/location" to location,
            "/hotels/$hotelID/clean" to clean,
            "/hotels/$hotelID/service" to service,
            "/hotels/$hotelID/convenience" to convenience,
        )

        database.updateChildren(ratingUpdate)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback.invoke("success")
                } else {
                    callback.invoke("failure")
                }
            }
    }
}