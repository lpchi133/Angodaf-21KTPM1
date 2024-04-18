package com.example.angodafake

import android.annotation.SuppressLint
import android.app.Dialog
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
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
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
    private lateinit var closeDialog: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var linearAdapter: VoucherHotelAdapter
    private var listVoucher: MutableList<Voucher> = mutableListOf()

    private lateinit var promotion: TextView
    private lateinit var priceAfterPromotion: TextView

    private fun createData(hotel_ID: String) {
        listVoucher.clear()

        VoucherUtils.getAllVouchers(hotel_ID) {vouchers ->
            listVoucher = vouchers
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
            VoucherUtils.minusVoucher("1",10) {result ->
                println(result)
            }
        }

        createData("1")

        layoutManager = LinearLayoutManager(this)
        seenVoucherBtn = findViewById(R.id.seen_voucher)
        promotion = findViewById(R.id.Promotion)
        priceAfterPromotion = findViewById(R.id.PriceAfterPromotion)

        seenVoucherBtn.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun showBottomSheet() {
        var newPrice: Double
        val fieldVoucher = BottomSheetDialog(this)

        fieldVoucher.requestWindowFeature(Window.FEATURE_NO_TITLE)
        fieldVoucher.setCancelable(false)
        fieldVoucher.setContentView(R.layout.custom_my_voucher)
        fieldVoucher.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        recyclerView = fieldVoucher.findViewById(R.id.voucher_area)!!
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        linearAdapter = VoucherHotelAdapter(this, listVoucher)
        recyclerView.adapter = linearAdapter

        linearAdapter.onItemClick = { contact ->
            if (contact.money_discount == 0.0) {
                newPrice = calculatePrice2(1200000.0, contact.max_discount!!, contact.percentage!!)
            } else {
                newPrice = calculatePrice1(1200000.0, contact.money_discount!!)
            }
            priceAfterPromotion.text = format(newPrice)
            fieldVoucher.dismiss()
        }

        closeDialog = fieldVoucher.findViewById(R.id.btn_back)!!
        closeDialog.setOnClickListener {
            fieldVoucher.dismiss()
        }

        fieldVoucher.show()
    }

    @SuppressLint("SetTextI18n")
    private fun calculatePrice1(olePrice: Double, money_discount: Double): Double {
        promotion.text = "- ${format(money_discount)}"
        return olePrice - money_discount
    }

    @SuppressLint("SetTextI18n")
    private fun calculatePrice2(olePrice: Double, max_discount: Double, percentage: Int): Double {
        val money_discount = olePrice * percentage * 0.01
        promotion.text = "- ${format(money_discount)}"
        return if (money_discount >= max_discount) {
            olePrice - max_discount
        } else {
            olePrice - money_discount
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
