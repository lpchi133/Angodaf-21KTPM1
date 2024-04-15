package com.example.angodafake

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class CancelPurchase : AppCompatActivity() {
    private fun showpopup_2() {
        println("OK")
        val diaLog = Dialog(this)
        diaLog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        diaLog.setCancelable(false)
        diaLog.setContentView(R.layout.popup_2)
        diaLog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val window = diaLog.window
        val layoutParams = window?.attributes
        layoutParams?.width = ViewGroup.LayoutParams.MATCH_PARENT // Kích thước ngang theo match_parent
        layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT // Kích thước chiều cao tự động
        window?.attributes = layoutParams
        val btnMaintain: Button = diaLog.findViewById(R.id.btn_maintain)
        val btnCancel: Button = diaLog.findViewById(R.id.btn_cancel)

        btnMaintain.setOnClickListener {
            finish()
        }

        btnCancel.setOnClickListener {
            Toast.makeText(this, "Đã hủy thành công đặt phòng của bạn!", Toast.LENGTH_SHORT).show()
            finish()
        }

        diaLog.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cancel_purchase)

        val myList = listOf("Chọn một trong số các lý do sau",
            "Đặt phòng không được xác nhận kịp thời",
            "Không quá tin tưởng vào uy tín của dịch vụ chúng tôi",
            "Lo lắng về sự an toàn cho vị trí khách sạn",
            "Quyết định chọn khách sạn khác trên Agoda",
            "Không thích chính sách hủy phòng",
            "Không hài lòng với cách thanh toán",
            "Tìm thấy giá thấp hơn trên mạng",
            "Tìm được giá thấp hơn qua dịch vụ địa phương",
            "Thiên tai",
            "Sẽ đặt khách sạn khác trên app của chúng tôi",
            "Sẽ đặt phòng trực tiếp với khách sạn",
            "Khác")

        val spinner: Spinner = findViewById(R.id.spinner)
        val btnBack: ImageButton = findViewById(R.id.btn_back)
        val btnContinue: Button = findViewById(R.id.btn_continue)
        val notic: TextView = findViewById(R.id.notic)

        btnBack.setOnClickListener {
            finish()
        }

        btnContinue.setOnClickListener {
            val selectedPosition = spinner.selectedItemPosition
            if (selectedPosition == 0) {
                notic.text = "*Vui lòng chọn lý do hủy đặt phòng"
            } else {
                notic.text = ""
                showpopup_2()
            }
        }

        ArrayAdapter(this, android.R.layout.simple_spinner_item, myList)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
    }
}