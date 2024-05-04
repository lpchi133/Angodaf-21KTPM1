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

    fun addHotel(hotel: Hotel, listener: (String) -> Unit){
        val key = database.child("hotels").push().key
        if (key != null){
            database.child("hotels").child(key).setValue(hotel)
            listener(key)
        } else{
            Log.e("firebase","Counldn't get push key for hotel")
        }
    }

    fun updateHotel(ID: String, hotel: Hotel, listener: (String?) -> Unit){
        database.child("hotels").child(ID).setValue(hotel)
            .addOnSuccessListener {
                // Xử lý khi cập nhật thông tin khách sạn thành công
                listener(ID)
            }
            .addOnFailureListener { exception ->
                // Xử lý khi xảy ra lỗi
                Log.e("firebase", "Couldn't update hotel information: ${exception.message}")
                listener(null)
            }
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