package com.example.angodafake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.MultiAutoCompleteTextView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.Adapter.ChatAdapter
import com.example.angodafake.Utilities.ChatAdapterUtils
import com.example.angodafake.Utilities.UserUtils

class ChatOfHotel : AppCompatActivity() {
    private lateinit var idUser: String
    private lateinit var idPartner: String

    private lateinit var findField: MultiAutoCompleteTextView
    private lateinit var notic: TextView
    private lateinit var chatField: RecyclerView
    private lateinit var empty: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_chat_of_hotel)

        val btnBack: ImageButton = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        val nameHotel: TextView = findViewById(R.id.toolbar)
        nameHotel.text = intent.getStringExtra("nameHotel")

        idUser = intent.getStringExtra("ID_User").toString()
        idPartner = intent.getStringExtra("ID_Partner").toString()

        findField = findViewById(R.id.textView3)
        notic = findViewById(R.id.notic_empty_3)
        chatField = findViewById(R.id.hotelChatList)
        empty = findViewById(R.id.imageView3)

        notic.visibility = View.GONE
        chatField.visibility = View.GONE
        empty.visibility = View.GONE

        setUpData()
    }

    private fun setUpData() {
        val layoutManager: RecyclerView.LayoutManager
        var linearAdapter: ChatAdapter

        layoutManager = LinearLayoutManager(this)
        chatField.layoutManager = layoutManager
        chatField.setHasFixedSize(true)

        ChatAdapterUtils.getChatRoomByPartnerID(idPartner) { chatRooms ->
            if (chatRooms.size == 0) {
                notic.visibility = View.VISIBLE
            } else {
                chatField.visibility = View.VISIBLE

                linearAdapter = ChatAdapter(this, chatRooms, "seeClient")
                chatField.adapter = linearAdapter

                linearAdapter.onItemClick = { contact ->
                    val intent = Intent(this, ChatRoom::class.java)
                    intent.putExtra("ID_User", contact.ID_Partner)
                    intent.putExtra("ID_Partner", contact.ID_User)
                    intent.putExtra("Type_Partner", "User")
                    startActivity(intent)
                }
            }
        }
    }
}