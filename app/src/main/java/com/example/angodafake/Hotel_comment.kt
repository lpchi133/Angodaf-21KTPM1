package com.example.angodafake

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.Adapter.CommentAdapter
import com.example.angodafake.Utilities.CommentUtils
import com.example.angodafake.db.Comment

class Hotel_comment : AppCompatActivity() {
    private var commentList: MutableList<Comment> = mutableListOf()
    private lateinit var commentField: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_hotel_comment)

        val btnBack:ImageButton = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        val point:TextView = findViewById(R.id.textView1)
        point.text = intent.getStringExtra("point")

        val status:TextView = findViewById(R.id.textView2)
        status.text = intent.getStringExtra("rateStatus")

        val quantityComment: TextView = findViewById(R.id.textView3)
        quantityComment.text = intent.getStringExtra("cmt")

        val cleanDouble = intent.getStringExtra("clean")?.toDouble()
        val clean1:ProgressBar = findViewById(R.id.bar_condition1)
        clean1.progress = (cleanDouble!!*10).toInt()
        val clean2: TextView = findViewById(R.id.point_condition1)
        clean2.text = cleanDouble.toString()

        val convenienceDouble = intent.getStringExtra("convenience")?.toDouble()
        val convenience1:ProgressBar = findViewById(R.id.bar_condition2)
        convenience1.progress = (convenienceDouble!!*10).toInt()
        val convenience2:TextView = findViewById(R.id.point_condition2)
        convenience2.text = convenienceDouble.toString()

        val locationDouble = intent.getStringExtra("location")?.toDouble()
        val location1:ProgressBar = findViewById(R.id.bar_condition3)
        location1.progress = (locationDouble!!*10).toInt()
        val location2:TextView = findViewById(R.id.point_condition3)
        location2.text = locationDouble.toString()

        val serviceDouble = intent.getStringExtra("service")?.toDouble()
        val service1:ProgressBar = findViewById(R.id.bar_condition4)
        service1.progress = (serviceDouble!!*10).toInt()
        val service2:TextView = findViewById(R.id.point_condition4)
        service2.text = serviceDouble.toString()

        val moneyDouble = intent.getStringExtra("money")?.toDouble()
        val money1:ProgressBar = findViewById(R.id.bar_condition5)
        money1.progress = (moneyDouble!!*10).toInt()
        val money2:TextView = findViewById(R.id.point_condition5)
        money2.text = moneyDouble.toString()

        val btnSort:Button = findViewById(R.id.button)
        btnSort.setOnClickListener {
            val dialog = Dialog(this)
            val inflater = LayoutInflater.from(this)
            val dialogView = inflater.inflate(R.layout.popup_3, null)

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(dialogView)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val window = dialog.window
            val layoutParams = window?.attributes
            layoutParams?.width = ViewGroup.LayoutParams.MATCH_PARENT // Kích thước ngang theo match_parent
            layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT // Kích thước chiều cao tự động
            window?.attributes = layoutParams

            dialog.show()
        }

        val layoutManager: RecyclerView.LayoutManager
        var linearAdapter: CommentAdapter

        commentField = findViewById(R.id.commentField)

        layoutManager = LinearLayoutManager(this)
        commentField.layoutManager = layoutManager
        commentField.setHasFixedSize(true)

        CommentUtils.getAllComments(intent.getStringExtra("idHotel")!!) {comments ->
            commentList = comments
            println(commentList)

            linearAdapter = CommentAdapter(this, commentList)
            commentField.adapter = linearAdapter
        }
    }
}