package com.example.angodafake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.angodafake.databinding.ActivityMainBinding
import com.example.angodafake.db.Bookmarks
import com.example.angodafake.db.Hotel
import com.example.angodafake.db.HotelDatabase
import com.example.angodafake.db.Picture
import com.example.angodafake.db.User
import com.example.angodafake.db.Rooms
import com.example.angodafake.db.Purchase
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.annotations.TestOnly
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var hotel_db: HotelDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(Home())

//        ******* ADD DATABASE **********
        hotel_db = HotelDatabase.getInstance(this)

        addDatabase()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))


        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.home -> replaceFragment(Home())
                R.id.room -> replaceFragment(MyRoom())
                R.id.hotel -> replaceFragment(MyHotel())
                R.id.bookmark -> replaceFragment(Bookmark())
                R.id.profile -> replaceFragment(MyProfile())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    @TestOnly
    private fun addDatabase(){
        readUser()
        readHotel()
        readBookmark()
        readPicture()
        readRoom()
        readPurchase()
    }

    private fun readUser(){
        val inputStream = this.assets.open("user.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String? = reader.readLine()
        while (line != null) {
            val name = line
            val dob = reader.readLine()
            val gender = reader.readLine()
            val number = reader.readLine()
            val email = reader.readLine()
            val country = reader.readLine()
            val cardNumber = reader.readLine()
            val cardName = reader.readLine()
            val point = reader.readLine().toInt()
            val userName = reader.readLine()
            val password = reader.readLine()

            val user = User(name, dob, gender, number, email, country, cardNumber, cardName, point, userName, password)
            hotel_db.UserDAO().insertUser(user)
            println(user)
            line = reader.readLine()
        }
        reader.close()
    }

    private fun readHotel(){
        val inputStream = this.assets.open("hotel.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String? = reader.readLine()
        while (line != null) {
            val ID_Owner = line.toInt()
            val name = reader.readLine()
            val phoneNumber = reader.readLine()
            val locationDetail = reader.readLine()
            val city = reader.readLine()
            val description = reader.readLine()
            val conveniences = reader.readLine()
            val star = reader.readLine().toInt()
            val point = reader.readLine().toDouble()
            val profit = reader.readLine().toDouble()

            val hotel = Hotel(ID_Owner, name, phoneNumber, locationDetail, city, description, conveniences, star, point, profit)
            hotel_db.HotelDAO().insertHotel(hotel)
            println(hotel)
            line = reader.readLine()
        }
        reader.close()
    }

    private fun readBookmark(){
        val inputStream = this.assets.open("bookmark.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String? = reader.readLine()
        while (line != null) {
            val ID_Owner = line.toInt()
            val ID_Hotel = reader.readLine().toInt()

            val bookmark = Bookmarks(ID_Owner, ID_Hotel)
            hotel_db.BookmarkDAO().insertBookmark(bookmark)
            println(bookmark)
            line = reader.readLine()
        }
        reader.close()
    }

    private fun readPicture(){
        val inputStream = this.assets.open("picture.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String? = reader.readLine()
        while (line != null) {
            val ID_Hotel = line.toInt()
            val pictureID = reader.readLine()

            val picture = Picture(ID_Hotel, pictureID)
            hotel_db.PictureDAO().insertPicture(picture)
            println(picture)
            line = reader.readLine()
        }
        reader.close()
    }

    private fun readRoom(){
        val inputStream = this.assets.open("room.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String? = reader.readLine()
        while (line != null) {
            val ID_Hotel = line.toInt()
            val quantityRoom = reader.readLine().toInt()
            val availableRoom = reader.readLine().toInt()
            val typeRoom = reader.readLine()
            val acreageRoom = reader.readLine().toDouble()
            val directionRoom = reader.readLine()
            val benefitRoom = reader.readLine()
            val priceRoom = reader.readLine().toDouble()
            val bedQuantityRoom = reader.readLine()

            val room = Rooms(ID_Hotel, quantityRoom, availableRoom, typeRoom, acreageRoom, directionRoom, benefitRoom, priceRoom, bedQuantityRoom)
            hotel_db.RoomDAO().insertRoom(room)
            println(room)
            line = reader.readLine()
        }
        reader.close()
    }

    private fun readPurchase(){
        val inputStream = this.assets.open("purchase.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String? = reader.readLine()
        while (line != null) {
            val ID_Owner = line.toInt()
            val ID_Hotel = reader.readLine().toInt()
            val ID_Room = reader.readLine().toInt()
            val quantityP = reader.readLine().toInt()
            val capacityP = reader.readLine()
            val payment_methodP = reader.readLine()
            val time_booking = reader.readLine()
            val time_purchase = reader.readLine()
            val total_purchase = reader.readLine().toDouble()
            val agoda_money = reader.readLine().toDouble()
            val status_order = reader.readLine()
            val status_purchase = reader.readLine()
            val check_in = reader.readLine()
            val check_out = reader.readLine()

            val purchase = Purchase(ID_Owner, ID_Hotel, ID_Room, quantityP, capacityP, payment_methodP, time_booking, time_purchase, total_purchase, agoda_money, status_order, status_purchase, check_in, check_out)
            hotel_db.PurchaseDAO().insertPurchase(purchase)
            println(purchase)
            line = reader.readLine()
        }
        reader.close()
    }
}