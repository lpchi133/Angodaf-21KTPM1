package com.example.angodafake.Utilities

import com.example.angodafake.db.Picture_Hotel
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

object PictureUtils {
    private lateinit var database: DatabaseReference

    init {
        database = Firebase.database.reference

    }

    fun getPictureByHotelID(ID: String, listener: (Picture_Hotel) -> Unit){
        val pictureQuery = database.child("pictures").orderByChild("ID_Hotel").equalTo(ID)
        pictureQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Xử lý khi dữ liệu thay đổi
                val pictureHotelList = mutableListOf<Picture_Hotel>()
                for (pictureSnapshot in dataSnapshot.children) {
                    val pictureHotel = pictureSnapshot.getValue(Picture_Hotel::class.java)
                    pictureHotel?.let { pictureHotelList.add(it) }
                }

                if (!pictureHotelList.isEmpty()){
                    listener(pictureHotelList[0])
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })
    }

}