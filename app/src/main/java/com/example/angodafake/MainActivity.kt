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

            val hotel = Hotel(ID_Owner, name, phoneNumber, locationDetail,city,description, conveniences,star, point, profit)
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
}