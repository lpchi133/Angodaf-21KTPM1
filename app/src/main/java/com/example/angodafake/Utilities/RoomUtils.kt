package com.example.angodafake.Utilities

import android.util.Log
import com.example.angodafake.db.Bookmark
import com.example.angodafake.db.Hotel
import com.example.angodafake.db.Picture
import com.example.angodafake.db.Rooms
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

object RoomUtils {
    private lateinit var database: DatabaseReference

    init {
        database = Firebase.database.reference

    }

    fun getRoomByHotelID(ID: String, listener: (List<Rooms>) -> Unit){
        val roomQuery = database.child("rooms").orderByChild("ID_Hotel").equalTo(ID)
        roomQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Xử lý khi dữ liệu thay đổi
                val roomList = mutableListOf<Rooms>()
                for (roomSnapshot in dataSnapshot.children) {
                    val room = roomSnapshot.getValue(Rooms::class.java)
                    room?.let { roomList.add(it) }
                }
                listener(roomList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })
    }

    fun getRoomList(listener: (List<Rooms>) -> Unit) {
        val roomList = mutableListOf<Rooms>()
        database.child("rooms").get().addOnSuccessListener { dataSnapshot ->
            for (roomSnapshot in dataSnapshot.children) {
                val room = roomSnapshot.getValue(Rooms::class.java)
                room?.let {
                    roomList.add(it)
                }
            }
            listener(roomList)
        }.addOnFailureListener { exception ->
            Log.e("firebase", "Error getting hotel list", exception)
            listener(emptyList()) // Trả về danh sách rỗng nếu có lỗi xảy ra
        }
    }
}