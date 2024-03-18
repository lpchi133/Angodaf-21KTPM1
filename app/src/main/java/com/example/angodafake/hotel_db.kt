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

@Entity(tableName = "hotel_db")
data class Hotel (
    @ColumnInfo(name = "ID_Owner" )
    var ID_Owner : Int,
    @ColumnInfo(name = "name" )
    var name : String,
    @ColumnInfo(name = "location" )
    var location : String,
    @ColumnInfo(name = "description" )
    var description : String,
    @ColumnInfo(name = "conveniences" )
    var convenience : String,
    @ColumnInfo(name = "profit" )
    var profit : Double,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Dao
interface HotelDAO {
    @Query("Select * from hotel_db")
    fun getHotelList() : List<Hotel>
    @Insert
    fun insertHotel(hotel : Hotel)
    @Update
    fun updateHotel(hotel: Hotel)
    @Delete
    fun deleteHotel(hotel: Hotel)
}

@Database(entities = [Hotel::class], version = 1)
abstract class HotelDatabase : RoomDatabase() {
    abstract fun HotelDAO() : HotelDAO
    companion object {
        private const val DB_NAME = "hotel_db"
        private var instance: HotelDatabase? = null
        fun getInstance(context: Context): HotelDatabase {
            return instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, HotelDatabase::class.java,
                DB_NAME).allowMainThreadQueries().build()
    }
}