package com.example.angodafake

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.angodafake.Adapter.VoucherHotelAdapter
import com.example.angodafake.Utilities.HotelUtils
import com.example.angodafake.Utilities.RoomUtils
import com.example.angodafake.Utilities.UserUtils
import com.example.angodafake.Utilities.VoucherUtils
import com.example.angodafake.db.Purchase
import com.example.angodafake.db.Voucher
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import org.json.JSONException
import org.json.JSONObject
import vn.momo.momo_partner.AppMoMoLib
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Locale
class BookRoom : AppCompatActivity() {
    private lateinit var countdownTimer: CountDownTimer
    private lateinit var countdownTextView: TextView
    private lateinit var paymentMethodLayout: ConstraintLayout
    private lateinit var radioGroupPaymentMethod: RadioGroup
    private lateinit var radioGroupPaymentMethod2: RadioGroup
    private lateinit var bookRoomBtn: Button
    private lateinit var backBtn: ImageView

    private var firstPrice: Int = 0
    private var discountValue: Int = 0
    private val databaseReference = FirebaseDatabase.getInstance().reference.child("purchases")
    private lateinit var dialog: Dialog
    private lateinit var anim: LottieAnimationView
    private lateinit var hotelID: String
    private lateinit var RoomID: String
    private lateinit var purchaseId: String
    private lateinit var purchase: Purchase

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
    private var idVoucher: String = ""
    private var quantity: Int = 0
    private var newPrice: Int = 0
    private lateinit var hotelName: String
    private lateinit var typeRoom: String
    private val merchantCode = "MOMOC2IC20220510"

    private var SECRET_KEY = "sk_test_51PBOtERqFfsInlNh3xxgL5gxlFlzGT9DcvC39CdnYfOUEgzSaBLhIHZjd7FxeTOQmyxa0z7XUrgG6BLaUk4ZOkgd003tHSghUm"
    private var PUBLIC_KEY = "pk_test_51PBOtERqFfsInlNh3u5HEHArjlLgJXaeVXMtmWGNciKifAqtLgUr2aIueOjWc8hp5iHvGf8VEtU4OGchPgkQK1ZC00WEl82W2Y"
    private lateinit var paymentSheet: PaymentSheet
    private lateinit var stringRequest: StringRequest
    private lateinit var requestQueue: RequestQueue
    private lateinit var customerID: String
    private lateinit var ClientSecret: String
    private lateinit var EphericalKey: String

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

        AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT); // AppMoMoLib.ENVIRONMENT.PRODUCTION

        PaymentConfiguration.init(this, PUBLIC_KEY)
        paymentSheet = PaymentSheet(this) { paymentSheetResult ->
            onPaymentResult(paymentSheetResult)
        }
        println(paymentSheet)

        stringRequest = object : StringRequest(
            Method.POST,
            "https://api.stripe.com/v1/customers",
            { response ->
                // Xử lý phản hồi ở đây
                println("Response: $response")
                try {
                    val json = JSONObject(response)
                    customerID = json.getString("id")
                    getEphericalKey(customerID)
                } catch (e: JSONException){
                    e.printStackTrace()
                }
            },
            { error ->
                // Xử lý lỗi ở đây
                println("Error: ${error.networkResponse.statusCode}")
            }) {

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                // Thêm các tham số yêu cầu POST vào đây nếu cần
                return params
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                // Đặt các tiêu đề yêu cầu nếu cần
                headers["Authorization"] = "Bearer $SECRET_KEY"
                return headers
            }
        }

        requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)

        dialog = Dialog(this)
        dialog.setContentView(R.layout.purchased)
        anim = dialog.findViewById<LottieAnimationView>(R.id.checkPurchase)

        paymentMethodLayout = findViewById(R.id.paymentMethod)
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

        hotelID = intent.getStringExtra("hotelID").toString()
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
            hotelName = hotel.name.toString()
            findViewById<TextView>(R.id.textView43).text = "Hotline: ${hotel.phoneNumber}"
            dialog.findViewById<TextView>(R.id.hotelName).text = hotel.name
            dialog.findViewById<TextView>(R.id.timeCheckIn).text = "${hotel.checkIn} ${convertDateTimeToString(checkInTime, 3)}"
            dialog.findViewById<TextView>(R.id.timeCheckOut).text = "${hotel.checkOut} ${convertDateTimeToString(checkOutTime, 3)}"
        }

        val roomQuantity = intent.getStringExtra("roomQuantity").toString()
        RoomUtils.getRoomByID(hotelID, roomID!!){ room ->
            RoomID = room.ID.toString()
            findViewById<TextView>(R.id.type_room).text = "${roomQuantity} x ${room.type}"
            findViewById<TextView>(R.id.textView37).text = "Tối đa: ${room.capacity} người lớn"
            findViewById<TextView>(R.id.bedQ).text = "${room.single_bed} giường đơn - ${room.double_bed} giường đôi"
            firstPrice = (room.price?.times(roomQuantity.toInt()) ?: 1) * dayInHotel.toInt()
            findViewById<TextView>(R.id.Price).text = "${formatMoney(firstPrice)} đ"
            findViewById<TextView>(R.id.PriceAfterPromotion).text = "${formatMoney(firstPrice - discountValue)} đ"
            dialog.findViewById<TextView>(R.id.roomID).text = "${room.type}"
            typeRoom = room.type.toString()
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

        bookRoomBtn.setOnClickListener {
            purchaseId = getPurchaseID()

            bookRoom(hotelID, "tYw0x3oVS7gAd9wOdOszzvJMOEM2", roomID,
                roomQuantity, getTimePurchase(), (firstPrice - discountValue).toDouble(),
                checkInTime, checkOutTime, dialog, anim)

            dialog.setOnDismissListener(DialogInterface.OnDismissListener {
                backBtn.callOnClick()
            })

            if(idVoucher != ""){
                VoucherUtils.minusVoucher(idVoucher,quantity) {result ->
                    println(result)
                }
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

    private fun onPaymentResult(paymentSheetResult: PaymentSheetResult) {
        when (paymentSheetResult) {
            is PaymentSheetResult.Completed -> {
                println("Completed")
            }
            else -> {
                // Xử lý trường hợp mặc định hoặc các trường hợp khác
            }
        }
    }

    private fun getEphericalKey(customerID: String) {
        stringRequest = object : StringRequest(
            Method.POST,
            "https://api.stripe.com/v1/ephemeral_keys",
            { response ->
                // Xử lý phản hồi ở đây
                println("Response: $response")
                try {
                    val json = JSONObject(response)
                    EphericalKey = json.getString("id")
                    getClientSecret(customerID)
                } catch (e: JSONException){
                    e.printStackTrace()
                }
            },
            { error ->
                // Xử lý lỗi ở đây
                println("Error: ${error.message}")
            }) {

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["customer"] = customerID
                // Thêm các tham số yêu cầu POST vào đây nếu cần
                return params
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                // Đặt các tiêu đề yêu cầu nếu cần
                headers["Authorization"] = "Bearer $SECRET_KEY"
                headers["Stripe-version"] = "2024-04-10"
                headers["Content-Type"] = "application/x-www-form-urlencoded"
                return headers
            }
        }

        requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    private fun getClientSecret(customerID: String){
        stringRequest = object : StringRequest(
            Method.POST,
            "https://api.stripe.com/v1/payment_intents",
            { response ->
                // Xử lý phản hồi ở đây
                println("Response: $response")
                try {
                    val json = JSONObject(response)
                    ClientSecret = json.getString("client_secret")
                } catch (e: JSONException){
                    e.printStackTrace()
                }
            },
            { error ->
                // Xử lý lỗi ở đây
                println("Error: ${error.message}")
            }) {

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["customer"] = customerID
                params["amount"] = (firstPrice - discountValue).toString()
                params["currency"] = "vnd"
                params["automatic_payment_methods[enabled]"] = "true"
                return params
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                // Đặt các tiêu đề yêu cầu nếu cần
                headers["Authorization"] = "Bearer $SECRET_KEY"
                headers["Stripe-version"] = "2024-04-10"
                headers["Content-Type"] = "application/x-www-form-urlencoded"
                return headers
            }
        }

        requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    private fun PaymentFlow() {
        paymentSheet.presentWithPaymentIntent(
            ClientSecret, PaymentSheet.Configuration("Hotel name",
                PaymentSheet.CustomerConfiguration(
                    customerID, EphericalKey
                )
            )
        )
    }

    private fun showDialog(dialog: Dialog, anim: LottieAnimationView){
        dialog.show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable{
            anim.visibility = View.VISIBLE
            anim.playAnimation()
        }, 300)
    }

    private fun getPurchaseID(): String{
        val currentTime = Calendar.getInstance()
        val year = currentTime.get(Calendar.YEAR)
        val month = currentTime.get(Calendar.MONTH) + 1
        val day = currentTime.get(Calendar.DAY_OF_MONTH)
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)
        val second = currentTime.get(Calendar.SECOND)
        return  "$day$month$year$hour$minute$second"
    }

    private fun getTimePurchase(): String {
        val currentTime = Calendar.getInstance()
        // Định dạng thời gian theo đúng yêu cầu
        val dateFormat = SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault())

        return dateFormat.format(currentTime.time)
    }
    private fun bookRoom(hotelID: String, customerID: String, RoomID: String,
                         quantity: String, timeBooking: String, total_purchase: Double,
                         checkInTime: String, checkOutTime: String, dialog: Dialog, anim: LottieAnimationView){

        if(findViewById<RadioButton>(R.id.cashChoice).isChecked){
            // Trả tiền mặt -> lưu thông tin ngay vào DB
             purchase = Purchase(ID_Owner = customerID, ID_Hotel = hotelID, ID_Room = RoomID, quantity = quantity.toInt(),
                method = "CASH", time_booking = timeBooking, time_purchase = "", time_cancel = "", reason = "", total_purchase = total_purchase,
                status_purchase = "CHUA_THANH_TOAN", detail = "SAP_TOI", date_come = checkInTime, date_go = checkOutTime)
             databaseReference.child(purchaseId).setValue(purchase)
             showDialog(dialog, anim)
        } else if (findViewById<RadioButton>(R.id.onlineChoice).isChecked){
            // Kiểm tra vnpay hay momo
            if(findViewById<RadioButton>(R.id.cardChoice).isChecked){
                // CARD
                PaymentFlow()
//                val purchase = Purchase(ID_Owner = customerID, ID_Hotel = hotelID, ID_Room = RoomID, quantity = quantity.toInt(),
//                    method = "CARD_PAYMENT", time_booking = timeBooking, time_purchase = "$hour:$minute:$second $day/$month/$year", time_cancel = "", reason = "", total_purchase = total_purchase,
//                    status_purchase = "DA_THANH_TOAN", detail = "SAP_TOI", date_come = checkInTime, date_go = checkOutTime)
//                databaseReference.child(purchaseId).setValue(purchase)

            } else if(findViewById<RadioButton>(R.id.momoChoice).isChecked){
                // MoMo
                 purchase = Purchase(ID_Owner = customerID, ID_Hotel = hotelID, ID_Room = RoomID, quantity = quantity.toInt(),
                    method = "MOMO", time_booking = timeBooking, time_purchase = getTimePurchase(), time_cancel = "", reason = "", total_purchase = total_purchase,
                    status_purchase = "DA_THANH_TOAN", detail = "SAP_TOI", date_come = checkInTime, date_go = checkOutTime)
                 requestPayment(purchaseId, total_purchase.toInt())
            }
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
                calculatePrice2(firstPrice.toDouble(), contact.limit_price!!, contact.max_discount!!, contact.percentage!!)
            } else {
                calculatePrice1(firstPrice.toDouble(), contact.limit_price!!, contact.money_discount!!)
            }

            priceAfterPromotion.text = "${formatMoney(newPrice)} đ"

            recyclerView.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculatePrice1(olePrice: Double, limit_price: Double, money_discount: Double) {
        if (olePrice >= limit_price) {
            promotion.text = "- ${formatMoney(money_discount.toInt())} đ"
            newPrice = (olePrice - money_discount).toInt()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculatePrice2(olePrice: Double, limit_price: Double, max_discount: Double, percentage: Int){
        if (olePrice >= limit_price) {
            val money_discount = olePrice * percentage * 0.01
            newPrice = if (money_discount >= max_discount) {
                promotion.text = "- ${formatMoney(max_discount.toInt())} đ"
                (olePrice - max_discount).toInt()
            } else {
                promotion.text = "- ${formatMoney(money_discount.toInt())} đ"
                (olePrice - money_discount).toInt()
            }
        }
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

    //Get token through MoMo app
    private fun requestPayment(IDPayment: String, amount: Int) {
        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT)
        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN)

        val eventValue: MutableMap<String, Any> = HashMap()
        //client Required
        eventValue["merchantname"] = hotelName //Tên đối tác. được đăng ký tại https://business.momo.vn. VD: Google, Apple, Tiki , CGV Cinemas
        eventValue["merchantcode"] = merchantCode //Mã đối tác, được cung cấp bởi MoMo tại https://business.momo.vn
        eventValue["amount"] = amount //Kiểu integer
        eventValue["orderId"] = IDPayment //uniqueue id cho Bill order, giá trị duy nhất cho mỗi đơn hàng
        eventValue["orderLabel"] = "Payment ID: $IDPayment" //gán nhãn

        //client Optional - bill info
        eventValue["merchantnamelabel"] = "Đặt phòng khách sạn" //gán nhãn
        eventValue["fee"] = 0 //Kiểu integer
        eventValue["description"] = typeRoom //mô tả đơn hàng - short description

        //client extra data
        eventValue["requestId"] = merchantCode + "merchant_billId_" + System.currentTimeMillis()
        eventValue["partnerCode"] = merchantCode
        //Example extra data
        val objExtraData = JSONObject()
        try {
            objExtraData.put("ID_Hotel", hotelID)
            objExtraData.put("ID_Room", RoomID)
            objExtraData.put("quantity", quantity)
            objExtraData.put("totalPayment", amount)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        eventValue["extraData"] = objExtraData.toString()
        eventValue["extra"] = ""
        AppMoMoLib.getInstance().requestMoMoCallBack(this, eventValue)
    }

    //Get token callback from MoMo app an submit to server side
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
            if (data != null) {
                if (data.getIntExtra("status", -1) == 0) {
                    //TOKEN IS AVAILABLE
                    println("Thanh cong")
                    println("Get token " + data.getStringExtra("message"))
                    val token = data.getStringExtra("data") //Token response
                    val phoneNumber = data.getStringExtra("phonenumber")
                    var env = data.getStringExtra("env")
                    if (env == null) {
                        env = "app"
                    }
                    if (token != null && token != "") {
                        // TODO: send phoneNumber & token to your server side to process payment with MoMo server
                        // IF Momo topup success, continue to process your order
                        showDialog(dialog, anim)
                        databaseReference.child(purchaseId).setValue(purchase)
                    } else {
                        requestPayment(purchaseId, (firstPrice - discountValue))
                    }
                } else if (data.getIntExtra("status", -1) == 1) {
                    //TOKEN FAIL
                    val message =
                        if (data.getStringExtra("message") != null) data.getStringExtra("message") else "Thất bại"
                    println(message)
                } else if (data.getIntExtra("status", -1) == 2) {
                    //TOKEN FAIL
                    requestPayment(purchaseId, (firstPrice - discountValue))
                } else {
                    //TOKEN FAIL
                    requestPayment(purchaseId, (firstPrice - discountValue))
                }
            } else {
                requestPayment(purchaseId, (firstPrice - discountValue))
            }
        } else {
            requestPayment(purchaseId, (firstPrice - discountValue))
        }
    }
}