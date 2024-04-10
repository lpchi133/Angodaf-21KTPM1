package com.example.angodafake.Utilities

import com.example.angodafake.db.Bookmark
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

object BookmarkUtils {
    private lateinit var database: DatabaseReference

    init {
        database = Firebase.database.reference
    }

    fun getAllBookmarks(userID: String, listener: (List<Bookmark>) -> Unit){
        val bookmarksQuery = database.child("bookmarks").equalTo(userID, "ID_Owner")
        bookmarksQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Xử lý khi dữ liệu thay đổi
                val bookmarksList = mutableListOf<Bookmark>()
                for (bookmarkSnapshot in dataSnapshot.children) {
                    val bookmark = bookmarkSnapshot.getValue(Bookmark::class.java)
                    bookmark?.let { bookmarksList.add(it) }
                }
                listener(bookmarksList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })
    }
}