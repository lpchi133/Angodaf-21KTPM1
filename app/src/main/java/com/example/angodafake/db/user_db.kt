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

@Entity(tableName = "user_db")
data class User (
    @ColumnInfo(name = "name" )
    var name : String,
    @ColumnInfo(name = "dob" )
    var dob : String,
    @ColumnInfo(name = "gender")
    var gender : String,
    @ColumnInfo(name = "number")
    var number : String,
    @ColumnInfo(name = "email")
    var email : String,
    @ColumnInfo(name = "country")
    var country : String,
    @ColumnInfo(name = "cardNumber")
    var cardNumber : String,
    @ColumnInfo(name = "cardName")
    var cardName : String,
    @ColumnInfo(name = "point")
    var point : Int,
    @ColumnInfo(name = "userName")
    var userName : String,
    @ColumnInfo(name = "password")
    var password : String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Dao
interface UserDAO {
    @Query("Select * from user_db")
    fun getUserList() : List<User>
    @Query("SELECT * FROM user_db WHERE id = :user_id")
    fun getUserByID(user_id: Int): User
    @Insert
    fun insertUser(user : User)
    @Update
    fun updateUser(user: User)
    @Delete
    fun deleteUser(user: User)
}
