package com.example.angodafake

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView

class BookRoom : AppCompatActivity() {
    private lateinit var countdownTimer: CountDownTimer
    private lateinit var countdownTextView: TextView
    private lateinit var paymentMethodLayout: ConstraintLayout
    private lateinit var inputCardLayout: ConstraintLayout
    private lateinit var radioGroupPaymentMethod: RadioGroup
    private lateinit var radioGroupPaymentMethod2: RadioGroup
    private lateinit var bookRoomBtn: Button

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
}
