package com.example.angodafake.db

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

@Entity(tableName = "room_db")
data class Rooms (
    @ColumnInfo(name = "ID_Hotel" )
    var ID_Hotel : Int,
    @ColumnInfo(name = "quantity" )
    var quantity : Int,
    @ColumnInfo(name = "available" )
    var available : Int,
    @ColumnInfo(name = "type" )
    var type : String,
    @ColumnInfo(name = "acreage" )
    var acreage : Double,
    @ColumnInfo(name = "direction")
    var direction : String,
    @ColumnInfo(name = "benefit")
    var benefit : String,
    @ColumnInfo(name = "price" )
    var price : Double,
    @ColumnInfo(name = "bedQuantity" )
    var bedQuantity : Int,
    @ColumnInfo(name = "checkIn" )
    var checkIn : String,
    @ColumnInfo(name = "checkOut" )
    var checkOut : String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Dao
interface RoomDAO {
    @Query("Select * from room_db")
    fun getRoomList() : List<Rooms>
    @Insert
    fun insertRoom(room : Rooms)
    @Update
    fun updateRoom(room: Rooms)
    @Delete
    fun deleteRoom(room: Rooms)
}