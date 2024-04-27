package com.example.angodafake.Utilities

import com.example.angodafake.db.Comment
import com.example.angodafake.db.User
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

object CommentUtils {
    private lateinit var database: DatabaseReference
     init {
         database = Firebase.database.reference
     }

    fun getAllComments(ownerID: String, listenser: (MutableList<Comment>) -> Unit) {
        val commentsQuery = database.child("comments")

        commentsQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val commentsList = mutableListOf<Comment>()
                for (commentSnapshot in dataSnapshot.children) {
                    val comment = commentSnapshot.getValue(Comment::class.java)
                    if (comment?.ID_Hotel == ownerID) {
                        comment.ID = commentSnapshot.key
                        comment.let { commentsList.add(it) }
                    }
                }
                listenser(commentsList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}