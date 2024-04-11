package com.example.angodafake.Utilities

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

    fun getRoomsByHotelID(ID: String, listener: (List<Rooms>) -> Unit){
        val roomQuery = database.child("rooms").equalTo(ID, "ID_Hotel")
        roomQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Xử lý khi dữ liệu thay đổi
                val roomList = mutableListOf<Rooms>()
                for (roomSnapshot in dataSnapshot.children) {
                    val room = roomSnapshot.getValue(Rooms::class.java)
                    room?.let { roomList.add(it) }
                }
                if (!roomList.isEmpty()){
                    listener(roomList)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })
    }
}