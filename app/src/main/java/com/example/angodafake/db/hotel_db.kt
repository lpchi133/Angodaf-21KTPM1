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

@Entity(tableName = "hotel_db")
data class Hotel (
    @ColumnInfo(name = "ID_Owner" )
    var ID_Owner : Int,
    @ColumnInfo(name = "name" )
    var name : String,
    @ColumnInfo(name = "locationDetail" )
    var locationDetail : String,
    @ColumnInfo(name = "city" )
    var city : String,
    @ColumnInfo(name = "description" )
    var description : String,
    @ColumnInfo(name = "conveniences" )
    var convenience : String,
    @ColumnInfo(name = "point" )
    var point : Double,
    @ColumnInfo(name = "profit" )
    var profit : Double,
    @ColumnInfo(name = "checkIn" )
    var checkIn : String,
    @ColumnInfo(name = "checkOut" )
    var checkOut : String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Dao
interface HotelDAO {
    @Query("Select * from hotel_db")
    fun getHotelList() : List<Hotel>
    @Query("SELECT * FROM hotel_db WHERE id = :hotel_id")
    fun getHotelByID(hotel_id: Int): Hotel
    @Query("SELECT * FROM hotel_db WHERE locationDetail = :hotel_location")
    fun getHotelByLocation(hotel_location: String): Hotel
    @Query("SELECT * FROM hotel_db WHERE name = :hotel_name")
    fun getHotelByName(hotel_name: String): Hotel
    @Insert
    fun insertHotel(hotel : Hotel)
    @Update
    fun updateHotel(hotel: Hotel)
    @Delete
    fun deleteHotel(hotel: Hotel)
}

@Database(entities = [Hotel::class, Bookmarks::class, Picture::class, Comment::class, Purchase::class, Rooms::class, User::class], version = 1)
abstract class HotelDatabase : RoomDatabase() {
    abstract fun HotelDAO() : HotelDAO
    abstract fun BookmarkDAO() : BookmarkDAO
    abstract fun PictureDAO() : PictureDAO
    abstract fun CommentDAO() : CommentDAO
    abstract fun PurchaseDAO() : PurchaseDAO
    abstract fun RoomDAO() : RoomDAO
    abstract fun UserDAO() : UserDAO

    companion object {
        private const val DB_NAME = "hotel_db"
        private var instance: HotelDatabase? = null
        fun getInstance(context: Context): HotelDatabase {
            return instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, HotelDatabase::class.java,
                DB_NAME
            ).allowMainThreadQueries().build()
    }
}