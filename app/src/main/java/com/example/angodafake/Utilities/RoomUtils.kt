package com.example.angodafake.Utilities


import android.util.Log
import com.example.angodafake.db.Hotel
import com.example.angodafake.db.Rooms
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

object RoomUtils {
    private var database: DatabaseReference = Firebase.database.reference

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

    fun getRoomByID(ID_Hotel: String, roomID: String, listener: (Rooms) -> Unit) {
        database.child("rooms").child(ID_Hotel).child(roomID).get().addOnSuccessListener { dataSnapshot ->
            val room = dataSnapshot.getValue(Rooms::class.java)
            if (room != null) {
                listener(room)
            }
        }.addOnFailureListener { exception ->
            Log.e("firebase", "Error getting room by ID", exception)
        }
    }

    fun getRoomList(listener: (ArrayList<Rooms>) -> Unit) {
        val roomList = ArrayList<Rooms>()
        database.child("rooms").get().addOnSuccessListener { dataSnapshot ->
            for (roomSnapshot in dataSnapshot.children) {
                val roomArrayList = roomSnapshot.getValue(object : GenericTypeIndicator<ArrayList<Rooms>>() {})
                roomArrayList?.let {
                    for (room in it) {
                        roomList.add(room)
                    }
                }
            }
            listener(roomList)
        }.addOnFailureListener { exception ->
            Log.e("firebase", "Error getting hotel list", exception)
            listener(ArrayList()) // Trả về danh sách rỗng nếu có lỗi xảy ra
        }
    }

    fun getRoomsFromDatabase(listener: (List<Rooms>) -> Unit) {
        val roomsRef = database.child("rooms")

        roomsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val roomList = mutableListOf<Rooms>()

                for (roomSnapshot in dataSnapshot.children) {
                    for (roomDataSnapshot in roomSnapshot.children) {
                        val room = roomDataSnapshot.getValue(Rooms::class.java)
                        room?.let {
                            it.ID_Hotel = roomSnapshot.key
                            it.ID = roomDataSnapshot.key
                            roomList.add(it)
                        }
                    }
                }

                // Gửi danh sách phòng tới listener
                listener(roomList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })
    }

    fun addRoom(room: Rooms, idHotel: String, listener: (String) -> Unit){
        val key = database.child("rooms").child(idHotel).push().key
        if (key != null){
            database.child("rooms").child(idHotel).child(key).setValue(room)
            listener(key)
        } else{
            Log.e("firebase","Counldn't get push key for hotel")
        }
    }
}