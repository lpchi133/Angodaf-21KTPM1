package com.example.angodafake.Utilities

import com.example.angodafake.db.Bookmark
import com.example.angodafake.db.Hotel
import com.example.angodafake.db.User
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

object UserUtils {
    private lateinit var database: DatabaseReference

    init {
        database = Firebase.database.reference
    }

    fun getUserByID(ID: String, listener: (User) -> Unit){
        val usersQuery = database.child("users/$ID")
        usersQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Xử lý khi dữ liệu thay đổi
                val user = dataSnapshot.getValue(User::class.java)
                listener(user!!)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })
    }
}