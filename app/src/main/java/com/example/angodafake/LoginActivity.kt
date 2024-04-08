package com.example.angodafake

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TabHost
import android.widget.TextView
import com.example.angodafake.db.HotelDatabase
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.toxicbakery.bcrypt.Bcrypt

class LoginActivity : AppCompatActivity() {
    private lateinit var tabHost : TabHost
    private lateinit var hotel_db: HotelDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        hotel_db = HotelDatabase.getInstance(this)

        tabHost = findViewById(R.id.tabHost)
        tabHost.setup()

        var tabSpec : TabHost.TabSpec? = null

        tabSpec = tabHost.newTabSpec("tab_email")
        tabSpec.setIndicator("Email", null)
        tabSpec.setContent(R.id.tab_email)
        tabHost.addTab(tabSpec)

        tabSpec = tabHost.newTabSpec("tab_phone")
        tabSpec.setIndicator("Di động", null)
        tabSpec.setContent(R.id.tab_phone)
        tabHost.addTab(tabSpec)

        setUpTabEmail()
        tabHost.setOnTabChangedListener { tabId ->
            when (tabId) {
                "tab_email" -> {
                    setUpTabEmail()
                }
                "tab_phone" -> {
                    setUpTabPhoneN()
                }
            }
        }
    }
    private fun setUpTabEmail(){
        val lEmail = findViewById<TextInputLayout>(R.id.lEmail)
        val etEmail = lEmail.editText as TextInputEditText
        val lPass = findViewById<TextInputLayout>(R.id.lPass)
        val etPass = lPass.editText as TextInputEditText

        findViewById<Button>(R.id.btn_login_email).setOnClickListener {
            hideKeyboard(this)
            val email = etEmail.text.toString().trim()
            val pass = etPass.text.toString().trim()
            val checkEmail = validateEmail(email, lEmail)
            val checkPass = validatePass(pass, lPass)
            if (checkEmail && checkPass){
                //Thong tin nhap vao hop le
                if (!checkUserWithEmail(email, pass)){
                    //Thong tin sai
                    showSnackBar("Email hoặc Mật khẩu không đúng.")
                }
                else{
                    //Dang nhap thanh cong
                    val user = hotel_db.UserDAO().getUserByEmail(email)
                    showSuccessSnackBar("Đăng nhập thành công!")
                    val handler = Handler()
                    handler.postDelayed({
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("idUser", user!!.id.toString())
                        startActivity(intent)
                        finish()
                    }, 1000)
                }
            }
        }

        findViewById<TextView>(R.id.register).setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            intent.putExtra("with", "email")
            startActivity(intent)
            finish()
        }
    }

    private fun setUpTabPhoneN(){
        val lPhoneN = findViewById<TextInputLayout>(R.id.lPhoneN)
        val etPhoneN = lPhoneN.editText as TextInputEditText

        val lPass2 = findViewById<TextInputLayout>(R.id.lPass2)
        val etPass2 = lPass2.editText as TextInputEditText

        findViewById<Button>(R.id.btn_login_phoneN).setOnClickListener {
            hideKeyboard(this)
            val phoneN = etPhoneN.text.toString().trim()
            val pass = etPass2.text.toString().trim()
            val checkPhoneN = validatePhoneNumber(phoneN, lPhoneN)
            val checkPass = validatePass(pass, lPass2)
            if (checkPhoneN && checkPass){
                //Thong tin nhap vao hop le
                if (!checkUserWithPhoneN(phoneN, pass)){
                    //Thong tin sai
                    showSnackBar("Số di động hoặc Mật khẩu không đúng.")
                }
                else{
                    //Dang nhap thanh cong
                    val user = hotel_db.UserDAO().getUserByPhoneNumber(phoneN)
                    showSuccessSnackBar("Đăng nhập thành công!")
                    val handler = Handler()
                    handler.postDelayed({
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("idUser", user!!.id.toString())
                        startActivity(intent)
                        finish()
                    }, 1000)
                }

            }
        }
        findViewById<TextView>(R.id.register2).setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            intent.putExtra("with", "phoneNumber")
            startActivity(intent)
            finish()
        }
    }

    private fun checkUserWithEmail(email: String, pass: String): Boolean {
        val user = hotel_db.UserDAO().getUserByEmail(email)
        return if (user != null) {
            Bcrypt.verify(pass, user.password.toByteArray())
        } else {
            false
        }
    }
    private fun checkUserWithPhoneN(phoneN: String, pass: String): Boolean {
        val user = hotel_db.UserDAO().getUserByPhoneNumber(phoneN)
        return if (user != null) {
            Bcrypt.verify(pass, user.password.toByteArray())
        } else {
            false
        }
    }

    private fun validateEmail(email: String, lEmail: TextInputLayout): Boolean {
        return if (email == ""){
            lEmail.error = "Email không được để trống."
            false
        } else{
            val emailPattern = Regex("^([a-z\\d\\.-]+)@([a-z\\d-]+)\\.([a-z]{2,8})(\\.[a-z]{2,8})?\$")
            if (!email.matches(emailPattern)){
                lEmail.error = "Định dạng email không hợp lệ."
                false
            } else{
                lEmail.error = null
                true
            }
        }
    }

    private fun validatePhoneNumber(phoneN: String, lPhoneN: TextInputLayout): Boolean {
        return if (phoneN == ""){
            lPhoneN.error = "Số di động không được để trống."
            false
        } else{
            if (phoneN.length != 10){
                lPhoneN.error = "Số di động không hợp lệ."
                false
            } else{
                lPhoneN.error = null
                true
            }
        }
    }


    private fun validatePass(pass: String, lPass: TextInputLayout): Boolean {
        return if (pass == ""){
            lPass.error = "Mật khẩu không được để trống."
            false
        } else if (pass.length < 8){
            lPass.error = "Mật khẩu phải có ít nhất 8 kí tự."
            false
        } else{
            lPass.error = null
            true
        }
    }
    @SuppressLint("RestrictedApi")
    private fun showSnackBar(msg: String) {
        val snackbar = Snackbar.make(findViewById(R.id.rootView), msg, Snackbar.LENGTH_LONG)
        // Đổi màu background của Snackbar
        snackbar.view.backgroundTintList = ColorStateList.valueOf(Color.RED)
        snackbar.setTextColor(Color.WHITE)
        snackbar.show()
    }
    private fun showSuccessSnackBar(msg: String) {
        val snackbar = Snackbar.make(findViewById(R.id.rootView), msg, Snackbar.LENGTH_LONG)
        snackbar.view.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3193FF"))
        snackbar.setTextColor(Color.WHITE)
        snackbar.show()
    }

    fun hideKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        // Kiểm tra xem view nào đang có focus
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        // Sử dụng InputMethodManager để ẩn bàn phím
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        hotel_db.close()
    }

}