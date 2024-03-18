package com.example.angodafake

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

@Database(entities = [Rooms::class], version = 1)
abstract class RoomsDatabase : RoomDatabase() {
    abstract fun RoomDAO() : RoomDAO
    companion object {
        private const val DB_NAME = "room_db"
        private var instance: RoomsDatabase? = null
        fun getInstance(context: Context): RoomsDatabase {
            return instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, RoomsDatabase::class.java,
                DB_NAME).allowMainThreadQueries().build()
    }
}