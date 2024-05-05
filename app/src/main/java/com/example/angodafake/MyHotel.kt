package com.example.angodafake

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation.AnimationListener
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.Adapter.HotelManageAdapter
import com.example.angodafake.Utilities.HotelUtils
import com.example.angodafake.Utilities.PurchaseUtils
import com.example.angodafake.db.Hotel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [MyHotel.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyHotel(private var idUser: String) : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var openCommentActivity: Button

    private lateinit var addLL : View
    private lateinit var addFab : FloatingActionButton
    private lateinit var voucherLL : View
    private lateinit var voucherFab : FloatingActionButton
    private lateinit var statisticsLL : View
    private lateinit var statisticsFab : FloatingActionButton
    private lateinit var billLL : View
    private lateinit var billFab : FloatingActionButton
    private lateinit var qrLL : View
    private lateinit var qrFab : FloatingActionButton
    private lateinit var menuFab : FloatingActionButton
    private var rotate = false

    private lateinit var lDate : TextInputLayout
    private lateinit var et_date : TextInputEditText
    private lateinit var recyclerView : RecyclerView
    private lateinit var hotel_list : ArrayList<Hotel>
    private lateinit var date: String
    private lateinit var adapter: HotelManageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_hotel, container, false)
        initUI(view)
        initShowout(addLL)
        initShowout(voucherLL)
        initShowout(statisticsLL)
        initShowout(billLL)
        initShowout(qrLL)

        adapter = HotelManageAdapter(requireContext(), hotel_list, date)
        adapter.onItemClick = {
            val arg = Bundle()
            arg.putString("date", adapter.date)
            arg.putString("idHotel", it.ID)

            val manageRoomFrg = ManageRoomsFragment(idUser)
            manageRoomFrg.arguments = arg
            val mainActivity = requireActivity() as MainActivity
            mainActivity.replaceFragment(manageRoomFrg)
        }
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        HotelUtils.getHotelByOwnerID(idUser){hotelL->
            hotel_list.clear()
            hotel_list.addAll(hotelL)
            adapter.notifyDataSetChanged()
        }

        et_date.setOnClickListener {
            it.clearFocus()
            val year: Int
            val month: Int
            val day: Int
            if (et_date.text.toString() == ""){
                // Lấy ngày hiện tại
                val calendar = Calendar.getInstance()
                year = calendar.get(Calendar.YEAR)
                month = calendar.get(Calendar.MONTH)
                day = calendar.get(Calendar.DAY_OF_MONTH)
            }
            else{
                val dateParts  = et_date.text.toString().split("/")
                year = dateParts[2].toInt()
                month = dateParts[1].toInt() - 1
                day = dateParts[0].toInt()
            }
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { view: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
                    // Xử lý khi người dùng chọn ngày
                    val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                    et_date.setText(selectedDate)
                    adapter.date = et_date.text.toString()
                    adapter.notifyDataSetChanged()
                },
                year, month, day
            )
            datePickerDialog.show()
        }

        addFab.setOnClickListener {
            val mainActivity = requireActivity() as MainActivity
            mainActivity.replaceFragment(AddHotelFragment(idUser))
        }

        voucherFab.setOnClickListener {
            val intent = Intent(context, HotelOfManagementVoucher::class.java)
            intent.putExtra("id_user", idUser)
            startActivity(intent)
        }

        statisticsFab.setOnClickListener {

        }

        billFab.setOnClickListener {
            PurchaseUtils.getAllPurchaseByIDHotelOwner(idUser){
                Log.d("test_purchase", it.toString())

                val list = ArrayList<String>()
                for (bill in it!!){
                    list.add(bill.ID!!)
                }

                val arg = Bundle()
                arg.putString("from", "edit")
                arg.putString("date", et_date.text.toString())
                arg.putStringArrayList("bills", list)

                val billFrag = BillFragment(idUser)
                billFrag.arguments = arg

                val mainActivity = requireActivity() as MainActivity
                mainActivity.replaceFragment(billFrag)
            }
        }

        qrFab.setOnClickListener {

        }

        menuFab.setOnClickListener {
            toggleFabMode(it)
        }

        return view
    }

    private fun toggleFabMode(v: View) {
        rotate = rotateFab(v, !rotate)
        if (rotate){
            showIn(addLL)
            showIn(voucherLL)
            showIn(statisticsLL)
            showIn(billLL)
            showIn(qrLL)
            requireView().findViewById<View>(R.id.backgroundOverlay).visibility = View.VISIBLE
        } else{
            showOut(addLL)
            showOut(voucherLL)
            showOut(statisticsLL)
            showOut(billLL)
            showOut(qrLL)
            requireView().findViewById<View>(R.id.backgroundOverlay).visibility = View.GONE
        }
    }

    private fun initShowout(v: View){
        v.apply {
            visibility = View.GONE
            translationY = height.toFloat()
            alpha = 0f
        }
    }
    private fun showOut(view: View) {
        view.apply {
            visibility = View.VISIBLE
            alpha = 1f
            translationY = 0f
            animate()
                .setDuration(200)
                .translationY(height.toFloat())
                .setListener(object : AnimatorListenerAdapter(){
                })
                .alpha(0f)
                .start()
        }
    }

    private fun showIn(view: View) {
        view.apply {
            visibility = View.VISIBLE
            alpha = 0f
            translationY = height.toFloat()
            animate()
                .setDuration(200)
                .translationY(0f)
                .setListener(object : AnimatorListenerAdapter(){})
                .alpha(1f)
                .start()
        }
    }

    private fun rotateFab(v: View, rotate: Boolean): Boolean {
        v.animate()
            .setDuration(200)
            .setListener(object : AnimatorListenerAdapter(){})
            .rotation(if (rotate) 180f else 0f)
        return rotate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        openCommentActivity = view.findViewById(R.id.button1)
//        openCommentActivity.setOnClickListener {
//            val intent = Intent(context, MyComment::class.java)
//            intent.putExtra("id_user", idUser)
//            startActivity(intent)
//        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUI(view: View){
        addLL = view.findViewById(R.id.addLL)
        addFab = view.findViewById(R.id.addFab)
        voucherLL = view.findViewById(R.id.voucherLL)
        voucherFab = view.findViewById(R.id.voucherFab)
        statisticsLL = view.findViewById(R.id.statisticsLL)
        statisticsFab = view.findViewById(R.id.statisticsFab)
        billLL = view.findViewById(R.id.billLL)
        billFab = view.findViewById(R.id.billFab)
        qrLL = view.findViewById(R.id.qrLL)
        qrFab = view.findViewById(R.id.qrFab)
        menuFab = view.findViewById(R.id.menuFab)

        lDate = view.findViewById(R.id.lDate)
        et_date = lDate.editText as TextInputEditText

        if (arguments?.getString("date") != null){
            et_date.text = Editable.Factory.getInstance().newEditable(arguments?.getString("date"))
        }

        recyclerView = view.findViewById(R.id.recyclerView)
        hotel_list = ArrayList()
        if (et_date.text.toString() == ""){
            // Lấy ngày hiện tại
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val formattedDay = if (day < 10) "0$day" else "$day"
            val formattedMonth = if (month < 10) "0$month" else "$month"
            et_date.text = Editable.Factory.getInstance().newEditable("$formattedDay/$formattedMonth/$year")
        }
        date = et_date.text.toString()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyHotel.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(idUser: String) =
            MyHotel(idUser).apply {
                arguments = Bundle().apply {
                }
            }
    }
}