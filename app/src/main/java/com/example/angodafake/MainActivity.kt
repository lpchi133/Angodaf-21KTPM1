package com.example.angodafake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.angodafake.databinding.ActivityMainBinding
//import com.example.angodafake.db.Bookmarks
import com.example.angodafake.db.Hotel
//import com.example.angodafake.db.HotelDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.annotations.TestOnly
import java.io.BufferedReader
import java.io.InputStreamReader
import com.google.firebase.Firebase
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var idUser: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //idUser = intent.getStringExtra("idUser")?.toInt()
        idUser = 1
        replaceFragment(Home(idUser!!))


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))


        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.home -> replaceFragment(Home(idUser!!))
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
}