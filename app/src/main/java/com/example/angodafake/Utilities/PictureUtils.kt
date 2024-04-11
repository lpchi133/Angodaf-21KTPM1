package com.example.angodafake.Utilities

import android.util.Log
import com.example.angodafake.db.Bookmark
import com.example.angodafake.db.Hotel
import com.example.angodafake.db.Picture
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

    fun getPictureByHotelID(ID: String, listener: (Picture) -> Unit){
        val pictureQuery = database.child("pictures").orderByChild("ID_Hotel").equalTo(ID)
        pictureQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Xử lý khi dữ liệu thay đổi
                val pictureList = mutableListOf<Picture>()
                for (pictureSnapshot in dataSnapshot.children) {
                    val picture = pictureSnapshot.getValue(Picture::class.java)
                    picture?.let { pictureList.add(it) }
                }
                if (pictureList.isNotEmpty()) {
                    listener(pictureList[0])
                } else {
                    Log.d("FilterDetailFragment", "No picture found for hotel with ID $ID")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })
    }

}