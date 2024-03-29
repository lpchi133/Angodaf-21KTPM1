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

@Entity(tableName = "picture_db")
data class Picture (
    @ColumnInfo(name = "ID_Hotel" )
    var ID_Hotel : Int,
    @ColumnInfo(name = "picture" )
    var picture : String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
@Dao
interface PictureDAO {
    @Query("Select * from picture_db")
    fun getPictureList() : List<Picture>
    @Query("SELECT * FROM picture_db WHERE ID_Hotel = :hotel_id")
    fun getPictureByHotelID(hotel_id: Int): Picture
    @Insert
    fun insertPicture(picture: Picture)
    @Update
    fun updatePicture(picture: Picture)
    @Delete
    fun deletePicture(picture: Picture)
}