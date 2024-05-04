package com.example.angodafake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.MultiAutoCompleteTextView
import android.widget.TabHost
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.Adapter.ChatAdapter
import com.example.angodafake.Adapter.ChatHotelAdapter
import com.example.angodafake.Utilities.ChatAdapterUtils
import com.example.angodafake.Utilities.HotelUtils
import com.example.angodafake.db.Chat_Room
import com.example.angodafake.db.Hotel

class ChatList : AppCompatActivity() {
    private var customerChatList: MutableList<Chat_Room> = mutableListOf()
    private var hotelList: MutableList<Hotel> = mutableListOf()

    private lateinit var idUser: String

    private lateinit var findField1: EditText
    private lateinit var notic1: TextView
    private lateinit var chatField1: RecyclerView
    private lateinit var empty1: ImageView

    private lateinit var findFiled2: MultiAutoCompleteTextView
    private lateinit var notic2: TextView
    private lateinit var hotelField: RecyclerView
    private lateinit var empty2: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_chat_list)

        val btnBack: ImageButton = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        idUser = intent.getStringExtra("id_user").toString()

        findField1 = findViewById(R.id.textView1)
        notic1 = findViewById(R.id.notic_empty_1)
        chatField1 = findViewById(R.id.customerChatList)
        empty1 = findViewById(R.id.imageView1)

        findFiled2 = findViewById(R.id.textView2)
        notic2 = findViewById(R.id.notic_empty_2)
        hotelField = findViewById(R.id.hotelList)
        empty2 = findViewById(R.id.imageView2)

        notic1.visibility = View.GONE
        chatField1.visibility = View.GONE
        empty1.visibility = View.GONE

        notic2.visibility = View.GONE
        hotelField.visibility = View.GONE
        empty2.visibility = View.GONE

        val tabHost: TabHost = findViewById(R.id.tabHost)
        tabHost.setup()

        var tabSpec : TabHost.TabSpec?
        tabSpec = tabHost.newTabSpec("tab1")
        tabSpec.setIndicator("Từ khách sạn", null)
        tabSpec.setContent(R.id.tab1)
        tabHost.addTab(tabSpec)

        tabSpec = tabHost.newTabSpec("tab2")
        tabSpec.setIndicator("Từ khách hàng", null)
        tabSpec.setContent(R.id.tab2)
        tabHost.addTab(tabSpec)

        setUpTab1()
        tabHost.setOnTabChangedListener { tabId ->
            when (tabId) {
                "tab1" -> {
                    setUpTab1()
                }
                "tab2" -> {
                    setUpTab2()
                }
            }
        }
    }

    private fun setUpTab1() {
        val layoutManager: RecyclerView.LayoutManager
        var linearAdapter: ChatAdapter

        layoutManager = LinearLayoutManager(this)
        chatField1.layoutManager = layoutManager
        chatField1.setHasFixedSize(true)

        ChatAdapterUtils.getChatRoomByUserID(idUser) {chatRooms ->
            if (chatRooms.size == 0) {
                notic1.visibility = View.VISIBLE
            } else {
                chatField1.visibility = View.VISIBLE

                linearAdapter = ChatAdapter(this, chatRooms, "seeHotel")
                chatField1.adapter = linearAdapter

                linearAdapter.onItemClick = { contact ->
                    val intent = Intent(this, ChatRoom::class.java)
                    intent.putExtra("ID_User", contact.ID_User)
                    intent.putExtra("ID_Partner", contact.ID_Partner)
                    intent.putExtra("Type_Partner", "Hotel")
                    startActivity(intent)
                }
            }

//            HotelUtils.getHotelByChatRoom(chatRooms) {hotels ->
//                println(hotels)
//                findField1.addTextChangedListener(object : TextWatcher{
//                    override fun afterTextChanged(s: Editable?) {
//                        findField1.removeTextChangedListener(this)
//
//                        val text = s.toString().trim()
//
//                        if (text.isEmpty()) {
//                            customerChatList.clear()
//                            customerChatList = chatRooms
//                        } else {
//                            val resultHotelsSearch = hotels.filter { it.name?.lowercase()?.contains(text.lowercase()) == true } as MutableList<Hotel>
//
//                            println(resultHotelsSearch)
//                            val tempCustomerChatList = mutableListOf<Chat_Room>()
//
//                            for (result in resultHotelsSearch) {
//                                ChatAdapterUtils.getChatRoomByUserIDAndPartnerID(idUser, result.ID!!) { chatRoom ->
//                                    chatRoom?.let { tempCustomerChatList.add(it) }
//                                    println(chatRooms)
//
//                                }
//
//                                customerChatList.clear()
//                                customerChatList.addAll(tempCustomerChatList)
//                            }
//                        }
//                    }
//
//                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//                    }
//
//                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//
//                    }
//                })
//            }
        }
    }

    private fun setUpTab2() {
        val layoutManager: RecyclerView.LayoutManager
        var linearAdapter: ChatHotelAdapter

        layoutManager = LinearLayoutManager(this)
        hotelField.layoutManager = layoutManager
        hotelField.setHasFixedSize(true)

        HotelUtils.getHotelByOwnerID(idUser) {hotels ->
            if (hotels.size == 0) {
                notic2.visibility = View.VISIBLE
            } else {
                hotelField.visibility = View.VISIBLE

                linearAdapter = ChatHotelAdapter(this, hotels)
                hotelField.adapter = linearAdapter

                linearAdapter.onItemClick = { contact ->
                    val intent = Intent(this, ChatOfHotel::class.java)
                    intent.putExtra("ID_User", idUser)
                    intent.putExtra("ID_Partner", contact.ID)
                    intent.putExtra("nameHotel", contact.name)
                    startActivity(intent)
                }
            }
        }
    }
}