package com.example.angodafake

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ActivePurchaseDetail : AppCompatActivity() {
    private lateinit var btnBack: ImageButton
    private lateinit var btnCall: ImageButton
    private lateinit var btnLocal: ImageButton
    private lateinit var btnChat: Button
    private lateinit var btnRemove: Button
    private lateinit var btnOrder: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_active_purchase_detail)

        val statusPurchase = intent.getStringExtra("status_purchase")
        println(statusPurchase)
        if (statusPurchase == "Đã thanh toán") {
            val status2: TextView = findViewById(R.id.status2)
            status2.visibility = View.GONE
        } else {
            val status1: TextView = findViewById(R.id.status1)
            status1.visibility = View.GONE
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

        btnRemove = findViewById(R.id.btn_removeorder)
        btnRemove.setOnClickListener {
            val intent = Intent(this, CancelPurchase::class.java)
            this.startActivity(intent)
        }

        btnOrder = findViewById(R.id.btn_reorder)
        btnOrder.setOnClickListener {
            println("btnChat is pressed")
        }
    }
}