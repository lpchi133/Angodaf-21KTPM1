package com.example.angodafake.Utilities

import android.util.Log
import com.example.angodafake.db.User
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.tasks.await

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

//    suspend fun getUserByPhoneNumber(phoneN: String): User? {
//        return try {
//            val dataSnapshot = database.child("users").get().await()
//            var user: User? = null
//            dataSnapshot.children.forEach { userSnapshot ->
//                val currentUser = userSnapshot.getValue(User::class.java)
//                if (currentUser?.phoneN == phoneN) {
//                    user = currentUser
//                    return@forEach
//                }
//            }
//            user
//        } catch (e: Exception) {
//            null
//        }
//    }

    fun getUserByPhoneNumber(phoneN: String, listener: (User?) -> Unit){
        val usersQuery = database.child("users")
        usersQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var check = false
                for (userSnapshort in dataSnapshot.children){
                    val user = userSnapshort.getValue(User::class.java)
                    if (user!!.phoneN == phoneN){
                        check = true
                        listener(user)
                    }
                }
                if (check == false){
                    listener(null)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })
    }
}