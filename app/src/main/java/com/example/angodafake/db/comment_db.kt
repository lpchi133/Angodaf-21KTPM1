package com.example.angodafake.db

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity(tableName = "comment_db")
data class Comment (
    @ColumnInfo(name = "ID_Owner" )
    var ID_Owner : Int,
    @ColumnInfo(name = "ID_Hotel" )
    var ID_Hotel : Int,
    @ColumnInfo(name = "time" )
    var time : String,
    @ColumnInfo(name = "point" )
    var point : Float,
    @ColumnInfo(name = "detail" )
    var detail : String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Dao
interface CommentDAO {
    @Query("Select * from comment_db")
    fun getCommentList() : List<Comment>
    @Insert
    fun insertComment(comment : Comment)
    @Update
    fun updateComment(comment: Comment)
    @Delete
    fun deleteComment(comment: Comment)
}