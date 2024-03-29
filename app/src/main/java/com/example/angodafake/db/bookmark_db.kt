package com.example.angodafake.db

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

@Entity(tableName = "bookmark_db")
data class Bookmarks (
    @ColumnInfo(name = "ID_Owner" )
    var ID_Owner : Int,
    @ColumnInfo(name = "ID_Hotel" )
    var ID_Hotel : Int,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Dao
interface BookmarkDAO {
    @Query("Select * from bookmark_db")
    fun getBookmarkList() : List<Bookmarks>
    @Query("SELECT * FROM bookmark_db WHERE ID_Owner = :user_id")
    fun getBookmarksByUserID(user_id: Int): List<Bookmarks>
    @Insert
    fun insertBookmark(bookmark : Bookmarks)
    @Update
    fun updateBookmark(bookmark: Bookmarks)
    @Delete
    fun deleteBookmark(bookmark: Bookmarks)
}