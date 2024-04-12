package com.example.angodafake

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class PastCancelPurchaseDetail : AppCompatActivity() {
    private lateinit var btnBack: ImageButton
    private lateinit var btnCall: ImageButton
    private lateinit var btnLocal: ImageButton
    private lateinit var btnChat: Button
    private lateinit var btnOrder: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custome_past_cancel_purchase_detail)

        val status1: TextView = findViewById(R.id.status1)
        val status3: TextView = findViewById(R.id.status3)
        val status2: TextView = findViewById(R.id.status2)
        val statusPurchase = intent.getStringExtra("status_purchase")
        println(statusPurchase)
        if (statusPurchase == "Đã hoàn tiền") {
            status2.visibility = View.GONE
            status3.visibility = View.GONE
        } else if (statusPurchase == "Chưa hoàn tiền") {
            status1.visibility = View.GONE
            status3.visibility = View.GONE
        } else {
            status1.visibility = View.GONE
            status2.visibility = View.GONE
        }

        btnBack = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        btnCall = findViewById(R.id.btn_call)
        btnCall.setOnClickListener {
            val phone_number = "0767181815"
            val phone_uri = Uri.parse("tel:$phone_number")
            val phone_intent = Intent(Intent.ACTION_DIAL, phone_uri)
            startActivity(phone_intent)
        }

        btnLocal = findViewById(R.id.btn_local)
        btnLocal.setOnClickListener {
            println("btnLocal is pressed")
        }

        btnChat = findViewById(R.id.btn_chat)
        btnChat.setOnClickListener {
            println("btnChat is pressed")
        }

        btnOrder = findViewById(R.id.btn_reorder)
        btnOrder.setOnClickListener {
            println("btnChat is pressed")
        }
    }
}