package com.example.angodafake.Utilities


import android.util.Log
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

    fun getRoomByHotelID(ID_Hotel: String, listener: (List<Rooms>) -> Unit) {
        val roomList = mutableListOf<Rooms>()
        database.child("rooms").child(ID_Hotel).get().addOnSuccessListener { dataSnapshot ->
            for (roomSnapshot in dataSnapshot.children) {
                val room = roomSnapshot.getValue(Rooms::class.java)
                room?.let {
                    roomList.add(it)
                }
            }
            listener(roomList)
        }.addOnFailureListener { exception ->
            Log.e("firebase", "Error getting room list", exception)
            listener(emptyList()) // Trả về danh sách rỗng nếu có lỗi xảy ra
        }
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