package com.example.angodafake

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Build
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
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.utils.Utils
import com.example.angodafake.Utilities.HotelUtils
import com.example.angodafake.Utilities.RoomUtils
import com.example.angodafake.Utilities.UserUtils
import com.example.angodafake.db.Hotel
import com.example.angodafake.db.Rooms
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Locale
import com.example.angodafake.Adapter.VoucherHotelAdapter
import com.example.angodafake.Utilities.VoucherUtils
import com.example.angodafake.db.Voucher
import com.google.android.material.bottomsheet.BottomSheetDialog
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
    private lateinit var backBtn: ImageView

    private var discountValue: Int = 0

    private lateinit var changeCustomerBtn: Button
    private lateinit var specialDemand: TextInputEditText

    private lateinit var customerName: String
    private lateinit var customerEmail: String

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

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_room)

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.purchased)
        val anim = dialog.findViewById<LottieAnimationView>(R.id.checkPurchase)

        paymentMethodLayout = findViewById(R.id.paymentMethod)
        inputCardLayout = findViewById(R.id.cardInfor)
        countdownTextView = findViewById(R.id.timer)
        bookRoomBtn = findViewById(R.id.bookRoomBtn)
        backBtn = findViewById(R.id.back)

        val checkInTime = intent.getStringExtra("checkInTime") ?: ""
        val checkOutTime = intent.getStringExtra("checkOutTime") ?: ""
        findViewById<TextView>(R.id.checkIn).text = convertDateTimeToString(checkInTime, 1)
        findViewById<TextView>(R.id.checkOut).text = convertDateTimeToString(checkOutTime, 1)
        val dayInHotel = calculateDaysBetweenDates(checkInTime, checkOutTime).toString()
        findViewById<TextView>(R.id.day).text = dayInHotel

        findViewById<TextView>(R.id.hotelName).text = intent.getStringExtra("hotelName") ?: ""
        findViewById<TextView>(R.id.textView35).text = "Đặt phòng không có rủi ro! Quý khách có thể huỷ bỏ đến ${convertDateTimeToString(checkInTime, 2)} và không phải trả gì!"
        findViewById<TextView>(R.id.calender).text = "Đặt phòng hôm nay và thanh toán vào ${convertDateTimeToString(checkInTime, 2)}"

        val hotelID = intent.getStringExtra("hotelID")
        val roomID = intent.getStringExtra("roomID")
        HotelUtils.getHotelByID(hotelID!!){ hotel ->
            findViewById<TextView>(R.id.locationDetail).text = hotel.locationDetail
            findViewById<TextView>(R.id.rating).text = when (hotel.point?.toInt()){
                in 0 until 3 -> { "${hotel.point} Cực tệ" }
                in 3 until 5 -> { "${hotel.point} Tệ" }
                in 5 until 6 -> { "${hotel.point} Trung bình" }
                in 6 until 8 -> { "${hotel.point} Tốt" }
                in 8 until 9 -> { "${hotel.point} Rất tốt" }
                else -> { "${hotel.point} Tuyệt vời" }
            }
            findViewById<TextView>(R.id.textView43).text = "Hotline: ${hotel.phoneNumber}"
            dialog.findViewById<TextView>(R.id.hotelName).text = hotel.name
            dialog.findViewById<TextView>(R.id.timeCheckIn).text = "${hotel.checkIn} ${convertDateTimeToString(checkInTime, 3)}"
            dialog.findViewById<TextView>(R.id.timeCheckOut).text = "${hotel.checkOut} ${convertDateTimeToString(checkOutTime, 3)}"
        }

        val roomQuantity = intent.getStringExtra("roomQuantity").toString()
        RoomUtils.getRoomByID(hotelID, roomID!!){ room ->
            findViewById<TextView>(R.id.type_room).text = "${roomQuantity} x ${room.type}"
            findViewById<TextView>(R.id.textView37).text = "Tối đa: ${room.capacity} người lớn"
            findViewById<TextView>(R.id.bedQ).text = "${room.single_bed} giường đơn - ${room.double_bed} giường đôi"
            val firstPrice = (room.price?.times(roomQuantity.toInt()) ?: 1) * dayInHotel.toInt()
            findViewById<TextView>(R.id.Price).text = "${formatMoney(firstPrice)} đ"
            findViewById<TextView>(R.id.PriceAfterPromotion).text = "${formatMoney(firstPrice - discountValue)} đ"
            dialog.findViewById<TextView>(R.id.roomID).text = "${room.type}"
        }

        UserUtils.getUserByID("tYw0x3oVS7gAd9wOdOszzvJMOEM2"){user ->
            findViewById<TextView>(R.id.customerName).text = user.name
            dialog.findViewById<TextView>(R.id.customerName).text = user.name
            findViewById<TextView>(R.id.mailConfirm).text = "Chúng tôi sẽ gửi xác nhận đặt phòng của bạn đến ${user.email}. Vui lòng kiểm tra và xác nhận đặt phòng."
        }

        findViewById<TextView>(R.id.cost).text = "Giá gốc (${roomQuantity} phòng x ${dayInHotel} đêm)"
        findViewById<TextView>(R.id.Promotion).text = "${formatMoney(discountValue)} đ"

        findViewById<RadioButton>(R.id.cashChoice).text = "Thanh toán vào ${convertDateTimeToString(checkInTime, 2)}"
        findViewById<TextView>(R.id.textView47).text = "Đặt phòng hôm nay và thanh toán vào ${convertDateTimeToString(checkInTime, 2)}"
        findViewById<TextView>(R.id.textView48).text = "Huỷ đặt phòng trước 00:00, ${convertDateTimeToString(checkInTime, 2)}"

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

        backBtn.setOnClickListener {
            onBackPressed()
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

        createData(intent.getStringExtra("hotelID")!!)

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

    private fun convertDateTimeToString(dateTimeString: String, mode: Int): String {
        val dateTime = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(dateTimeString)
        val calendar = Calendar.getInstance()
        if (dateTime != null) {
            calendar.time = dateTime
        }

        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        val dayOfWeekString = when (dayOfWeek) {
            Calendar.MONDAY -> "T2"
            Calendar.TUESDAY -> "T3"
            Calendar.WEDNESDAY -> "T4"
            Calendar.THURSDAY -> "T5"
            Calendar.FRIDAY -> "T6"
            Calendar.SATURDAY -> "T7"
            Calendar.SUNDAY -> "CN"
            else -> ""
        }

        val monthString = when (month) {
            Calendar.JANUARY -> "tháng 1"
            Calendar.FEBRUARY -> "tháng 2"
            Calendar.MARCH -> "tháng 3"
            Calendar.APRIL -> "tháng 4"
            Calendar.MAY -> "tháng 5"
            Calendar.JUNE -> "tháng 6"
            Calendar.JULY -> "tháng 7"
            Calendar.AUGUST -> "tháng 8"
            Calendar.SEPTEMBER -> "tháng 9"
            Calendar.OCTOBER -> "tháng 10"
            Calendar.NOVEMBER -> "tháng 11"
            Calendar.DECEMBER -> "tháng 12"
            else -> ""
        }
        if (mode == 1) {
            return "$dayOfWeekString, $dayOfMonth $monthString"
        }
        else if (mode == 2) {
            return "ngày $dayOfMonth $monthString, $year"
        }
        else if (mode == 3){
            return "$dayOfWeekString, $dayOfMonth $monthString, $year"
        }
        return ""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateDaysBetweenDates(startDate: String, endDate: String): Long {
        val dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy")
        val startDateObj = LocalDate.parse(startDate, dateFormatter)
        val endDateObj = LocalDate.parse(endDate, dateFormatter)

        // Tính số ngày giữa hai ngày
        val daysBetween = ChronoUnit.DAYS.between(startDateObj, endDateObj)

        return daysBetween
    }

    fun formatMoney(amount: Int): String {
        val formatter = NumberFormat.getNumberInstance(Locale.getDefault()) as DecimalFormat
        formatter.applyPattern("#,###")
        return formatter.format(amount.toLong())
    }
}