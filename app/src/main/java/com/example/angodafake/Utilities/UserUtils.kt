package com.example.angodafake.Utilities

import android.util.Log
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

    fun getNameByID(ID: String, listener: (String) -> Unit){
        val usersQuery = database.child("users").child(ID)
        usersQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Xử lý khi dữ liệu thay đổi
                val user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    listener("User")
                }
                else{
                    listener(user.name!!)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })
    }

    fun getUserByPhoneNumber(phoneN: String, listener: (User?) -> Unit){
        val usersQuery = database.child("users")
        usersQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshort in dataSnapshot.children){
                    val user = userSnapshort.getValue(User::class.java)
                    Log.d("user", user.toString())
                    listener(user)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })
    }
}