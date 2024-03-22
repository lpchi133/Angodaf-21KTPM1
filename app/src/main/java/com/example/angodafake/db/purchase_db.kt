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

@Entity(tableName = "purchase_db")
data class Purchase (
    @ColumnInfo(name = "ID_Owner" )
    var ID_Owner : Int,
    @ColumnInfo(name = "ID_Hotel" )
    var ID_Hotel : Int,
    @ColumnInfo(name = "ID_Room" )
    var ID_Room : Int,
    @ColumnInfo(name = "time_booking" )
    var time_booking : String,
    @ColumnInfo(name = "time_purchase" )
    var time_purchase : String,
    @ColumnInfo(name = "total_purchase" )
    var total_purchase : Double,
    @ColumnInfo(name = "status_purchase" )
    var status_purchase : Boolean,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Dao
interface PurchaseDAO {
    @Query("Select * from purchase_db")
    fun getPurchaseList() : List<Purchase>
    @Insert
    fun insertPurchase(purchase : Purchase)
    @Update
    fun updatePurchase(purchase: Purchase)
    @Delete
    fun deletePurchase(purchase: Purchase)
}