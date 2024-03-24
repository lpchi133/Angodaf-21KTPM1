package com.example.angodafake

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.angodafake.db.HotelDatabase

class SplashActivity : AppCompatActivity() {
    private var idUser = -1
    private lateinit var hotel_db: HotelDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        hotel_db = HotelDatabase.getInstance(this)

        val handler = Handler()
        handler.postDelayed(nextActivity(), 2000)

    }

    private fun nextActivity(): Runnable {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        idUser = sharedPreferences.getInt("idUser", -1)
        Log.d("idUser", idUser.toString())

        return Runnable {
            if (idUser != -1 && hotel_db.UserDAO().getUserById(idUser) != null){
                //Da dang nhap truoc do
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("idUser", idUser.toString())
                startActivity(intent)
            }
            else{
                //Chua dang nhap, chuyen sang man hinh dang nhap
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        hotel_db.close()
    }
}