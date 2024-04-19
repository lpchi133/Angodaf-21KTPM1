package com.example.angodafake

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.text.Spanned
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.RadioGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.angodafake.Adapter.VoucherHotelAdapter
import com.example.angodafake.Utilities.VoucherUtils
import com.example.angodafake.db.Voucher
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import kotlin.properties.Delegates
import kotlin.time.times

class BookRoom : AppCompatActivity() {
    private lateinit var countdownTimer: CountDownTimer
    private lateinit var countdownTextView: TextView
    private lateinit var paymentMethodLayout: ConstraintLayout
    private lateinit var inputCardLayout: ConstraintLayout
    private lateinit var radioGroupPaymentMethod: RadioGroup
    private lateinit var radioGroupPaymentMethod2: RadioGroup
    private lateinit var bookRoomBtn: Button

    private lateinit var seenVoucherBtn: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var linearAdapter: VoucherHotelAdapter
    private var listVoucher: MutableList<Voucher> = mutableListOf()

    private lateinit var textView: TextView
    private lateinit var price: TextView
    private lateinit var promotion: TextView
    private lateinit var priceAfterPromotion: TextView
    private lateinit var idVoucher: String
    private var quantity: Int = 0
    private var newPrice: Double = 0.0

    private fun createData(hotel_ID: String) {
        listVoucher.clear()

        VoucherUtils.getAllVouchers(hotel_ID) {vouchers ->
            for (voucher in vouchers) {
                if (voucher.quantity!! > 0) {
                    listVoucher.add(voucher)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_room)

        paymentMethodLayout = findViewById(R.id.paymentMethod)
        inputCardLayout = findViewById(R.id.cardInfor)
        countdownTextView = findViewById(R.id.timer)
        bookRoomBtn = findViewById(R.id.bookRoomBtn)

        // Ẩn các layout khi khởi động Activity
        paymentMethodLayout.visibility = View.GONE
        inputCardLayout.visibility = View.GONE

        // Khởi tạo đồng hồ đếm ngược
        startCountdownTimer()

        // Xử lý sự kiện khi RadioButton trong RadioGroup Payment Method được chọn
        radioGroupPaymentMethod = findViewById(R.id.pickMethod)
        radioGroupPaymentMethod.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.cashChoice -> {
                    paymentMethodLayout.visibility = View.GONE
                }
                R.id.onlineChoice -> {
                    paymentMethodLayout.visibility = View.VISIBLE
                }
            }
        }

        // Xử lý sự kiện khi RadioButton trong RadioGroup Payment Method 2 được chọn
        radioGroupPaymentMethod2 = findViewById(R.id.radioGroup2)
        radioGroupPaymentMethod2.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.momoChoice -> {
                    inputCardLayout.visibility = View.GONE
                }
                R.id.cardChoice -> {
                    inputCardLayout.visibility = View.VISIBLE
                }
            }
        }

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.purchased)
        val anim = dialog.findViewById<LottieAnimationView>(R.id.checkPurchase)

        bookRoomBtn.setOnClickListener {
            dialog.show()
            Handler(Looper.getMainLooper()).postDelayed(Runnable{
                anim.visibility = View.VISIBLE
                anim.playAnimation()
            }, 300)
            VoucherUtils.minusVoucher(idVoucher,quantity) {result ->
                println(result)
            }
        }

        createData(intent.getStringExtra("hotel_ID")!!)

        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        seenVoucherBtn = findViewById(R.id.seen_voucher)
        textView = findViewById(R.id.textView39)
        price = findViewById(R.id.Price)
        promotion = findViewById(R.id.Promotion)
        priceAfterPromotion = findViewById(R.id.PriceAfterPromotion)

        priceAfterPromotion.text = price.text

        recyclerView = findViewById(R.id.fieldvoucher)

        seenVoucherBtn.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun showBottomSheet() {
        recyclerView.visibility = View.VISIBLE

        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        linearAdapter = VoucherHotelAdapter(this, listVoucher)
        recyclerView.adapter = linearAdapter

        linearAdapter.onItemClick = { contact ->
            idVoucher = contact.ID.toString()
            quantity = contact.quantity!!
            if (contact.money_discount == 0.0) {
                calculatePrice2(744000.0, contact.limit_price!!, contact.max_discount!!, contact.percentage!!)
            } else {
                calculatePrice1(744000.0, contact.limit_price!!, contact.money_discount!!)
            }

            priceAfterPromotion.text = format(newPrice)

            recyclerView.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculatePrice1(olePrice: Double, limit_price: Double, money_discount: Double) {
        if (olePrice >= limit_price) {
            promotion.text = "- ${format(money_discount)}"
            newPrice = olePrice - money_discount
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculatePrice2(olePrice: Double, limit_price: Double, max_discount: Double, percentage: Int){
        if (olePrice >= limit_price) {
            val money_discount = olePrice * percentage * 0.01
            promotion.text = "- ${format(money_discount)}"
            newPrice = if (money_discount >= max_discount) {
                olePrice - max_discount
            } else {
                olePrice - money_discount
            }
        }
    }

    fun format(money: Double): Spanned? {
        val formatSymbols = DecimalFormatSymbols()
        formatSymbols.groupingSeparator = '.'

        val decimalFormat = DecimalFormat("#,##0", formatSymbols)
        val temp = "${decimalFormat.format(money)} &#8363;"

        return Html.fromHtml(temp, Html.FROM_HTML_MODE_COMPACT)
    }

    private fun startCountdownTimer() {
        val countdownTimeInMillis: Long = 20 * 60 * 1000
        countdownTimer = object : CountDownTimer(countdownTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val formattedTime = formatTime(millisUntilFinished)
                countdownTextView.text = formattedTime
            }

            override fun onFinish() {
                countdownTextView.text = "00:00:00"
            }
        }
        countdownTimer.start()
    }

    private fun formatTime(timeInMillis: Long): String {
        val seconds = timeInMillis / 1000
        val minutes = seconds / 60
        val hours = minutes / 60

        val formattedTime = String.format("%02d:%02d:%02d",
            hours % 24,
            minutes % 60,
            seconds % 60
        )
        return formattedTime
    }
}
