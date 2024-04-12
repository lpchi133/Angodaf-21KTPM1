package com.example.angodafake.db

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

@Entity(tableName = "purchase_db")
data class Purchase(
    @ColumnInfo(name = "ID_Owner" )
    var ID_Owner: Int,
    @ColumnInfo(name = "ID_Hotel" )
    var ID_Hotel: Int,
    @ColumnInfo(name = "ID_Room" )
    var ID_Room: Int,
    @ColumnInfo(name = "quantity" )
    var quantity: Int,
    @ColumnInfo(name = "capacity")
    var capacity: String,
    @ColumnInfo(name = "payment_method")
    var payment_method: String,
    @ColumnInfo(name = "time_booking" )
    var time_booking: String,
    @ColumnInfo(name = "time_purchase" )
    var time_purchase: String,
    @ColumnInfo(name = "total_purchase" )
    var total_purchase: Double,
    @ColumnInfo(name = "agoda_money")
    var agoda_money: Double,
    @ColumnInfo(name = "status_order" )
    var status_order: String,
    @ColumnInfo(name = "status_purchase" )
    var status_purchase: String,
    @ColumnInfo(name = "checkIn" )
    var checkIn: String,
    @ColumnInfo(name = "checkOut" )
    var checkOut: String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Dao
interface PurchaseDAO {
    @Query("Select * from purchase_db")
    fun getPurchaseList() : List<Purchase>
    @Query("Select * from purchase_db where ID_Owner = :user_id")
    fun getPurchaseByUserID(user_id: Int): List<Purchase>
    @Insert
    fun insertPurchase(purchase : Purchase)
    @Update
    fun updatePurchase(purchase: Purchase)
    @Delete
    fun deletePurchase(purchase: Purchase)
}