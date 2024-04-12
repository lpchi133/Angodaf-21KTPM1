package com.example.angodafake

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.slider.RangeSlider

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [FilerDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class FilerDetail(private var idUser: Int) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var view: View
    private lateinit var rangeSlider: RangeSlider
    private var startValue: Float = 200000.0f
    private var endValue: Float = 900000.0f
    private var point: Double = 0.0
    private lateinit var text6Plus: TextView
    private lateinit var text7Plus: TextView
    private lateinit var text8Plus: TextView
    private lateinit var text9Plus: TextView
    private lateinit var slider: RangeSlider
    private lateinit var cbHanoi: CheckBox
    private lateinit var cbHCM: CheckBox
    private lateinit var cbNhaTrang: CheckBox
    private lateinit var cbDaLat: CheckBox
    private lateinit var cbHaiPhong: CheckBox
    private lateinit var cbDaNang: CheckBox

    private var isText6PlusSelected = false
    private var isText7PlusSelected = false
    private var isText8PlusSelected = false
    private var isText9PlusSelected = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home_filter_detail, container, false)
        val args = arguments
        var hotelIds = args?.getStringArray("hotelIds")
        val saveIds = args?.getStringArray("saveIds")
        val searchText = args?.getString("searchText")
        Log.d("FilterDetailFragment", "Hotel IDs Detail: ${hotelIds?.joinToString(", ")}, Search Text: $searchText")


        text6Plus = view.findViewById<TextView>(R.id.text_6_plus)
        text7Plus = view.findViewById<TextView>(R.id.text_7_plus)
        text8Plus = view.findViewById<TextView>(R.id.text_8_plus)
        text9Plus = view.findViewById<TextView>(R.id.text_9_plus)
        slider = view.findViewById(R.id.continuousSlider)
        cbHanoi = view.findViewById(R.id.cbHanoi)
        cbHCM = view.findViewById(R.id.cbHCM)
        cbNhaTrang = view.findViewById(R.id.cbNhaTrang)
        cbDaLat = view.findViewById(R.id.cbDaLat)
        cbHaiPhong = view.findViewById(R.id.cbHaiPhong)
        cbDaNang = view.findViewById(R.id.cbDaNang)

        val buttonReset: Button = view.findViewById(R.id.buttonReset)
        buttonReset.setOnClickListener {
            resetAllViews()
            point = 0.0
        }


        view.findViewById<Button>(R.id.backToList).setOnClickListener {
            val arg = Bundle()

            arg.putString("searchText", searchText)
            arg.putStringArray("hotelIds", hotelIds)
            arg.putStringArray("saveIds", saveIds)

            // Khởi tạo Fragment Filter và đính kèm Bundle
            val filterFragment = Filter(idUser)
            filterFragment.arguments = arg

            // Thay thế Fragment hiện tại bằng Fragment Filter
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, filterFragment)
                .addToBackStack(null)  // Để quay lại Fragment Home khi ấn nút Back
                .commit()
        }

        rangeSlider = view.findViewById(R.id.continuousSlider)

        rangeSlider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {
                // Lấy giá trị bắt đầu khi bắt đầu chạm vào slider
                startValue = slider.values[0]
                endValue = slider.values[1]
            }

            override fun onStopTrackingTouch(slider: RangeSlider) {
                // Lấy giá trị khi kết thúc chạm vào slider
                startValue = slider.values[0]
                endValue = slider.values[1]
            }
        })

        rangeSlider.addOnChangeListener { rangeSlider, value, fromUser ->
            // Lấy giá trị khi slider thay đổi
            startValue = rangeSlider.values[0]
            endValue = rangeSlider.values[1]
        }

        val selectedColor = Color.parseColor("#FFA500")
        text6Plus.setOnClickListener {
            setStatus()
            isText6PlusSelected = !isText6PlusSelected
            isText7PlusSelected = !isText7PlusSelected
            isText8PlusSelected = !isText8PlusSelected
            isText9PlusSelected = !isText9PlusSelected
            updateTextViewBackground(text6Plus, isText6PlusSelected, selectedColor)
            updateTextViewBackground(text7Plus, isText7PlusSelected, selectedColor)
            updateTextViewBackground(text8Plus, isText8PlusSelected, selectedColor)
            updateTextViewBackground(text9Plus, isText9PlusSelected, selectedColor)
            point = 6.0
        }

        text7Plus.setOnClickListener {
            setStatus()
            isText7PlusSelected = !isText7PlusSelected
            isText8PlusSelected = !isText8PlusSelected
            isText9PlusSelected = !isText9PlusSelected
            updateTextViewBackground(text6Plus, isText6PlusSelected, selectedColor)
            updateTextViewBackground(text7Plus, isText7PlusSelected, selectedColor)
            updateTextViewBackground(text8Plus, isText8PlusSelected, selectedColor)
            updateTextViewBackground(text9Plus, isText9PlusSelected, selectedColor)
            point = 7.0
        }

        text8Plus.setOnClickListener {
            setStatus()
            isText8PlusSelected = !isText8PlusSelected
            isText9PlusSelected = !isText9PlusSelected
            updateTextViewBackground(text6Plus, isText6PlusSelected, selectedColor)
            updateTextViewBackground(text7Plus, isText7PlusSelected, selectedColor)
            updateTextViewBackground(text8Plus, isText8PlusSelected, selectedColor)
            updateTextViewBackground(text9Plus, isText9PlusSelected, selectedColor)
            point = 8.0
        }

        text9Plus.setOnClickListener {
            setStatus()
            isText9PlusSelected = !isText9PlusSelected
            updateTextViewBackground(text6Plus, isText6PlusSelected, selectedColor)
            updateTextViewBackground(text7Plus, isText7PlusSelected, selectedColor)
            updateTextViewBackground(text8Plus, isText8PlusSelected, selectedColor)
            updateTextViewBackground(text9Plus, isText9PlusSelected, selectedColor)
            point = 9.0
        }

        val buttonOK: Button = view.findViewById(R.id.buttonOK)
        buttonOK.setOnClickListener {

            val bundle = Bundle()
            bundle.putDouble("point", point)
            bundle.putFloat("startValue", startValue)
            bundle.putFloat("endValue", endValue)
            bundle.putString("selectedCities", getSelectedCities())
            bundle.putStringArray("hotelIds", hotelIds)
            bundle.putString("searchText", searchText)
            bundle.putStringArray("saveIds", saveIds)

            val filterFragment = Filter(idUser)
            filterFragment.arguments = bundle

            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, filterFragment)
                .addToBackStack(null)  // Để quay lại Fragment Home khi ấn nút Back
                .commit()
        }
        return view
    }


    private fun updateTextViewBackground(textView: TextView, isSelected: Boolean, selectedColor: Int) {
        if (isSelected) {
            textView.setBackgroundColor(selectedColor)
        } else {
            textView.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    private fun setStatus() {
        isText6PlusSelected = false
        isText7PlusSelected = false
        isText8PlusSelected = false
        isText9PlusSelected = false
    }

    private fun resetAllViews() {
        // Đặt lại màu nền của TextViews về mặc định
        val allTextViews = arrayOf(text6Plus, text7Plus, text8Plus, text9Plus)
        for (textView in allTextViews) {
            textView.setBackgroundColor(Color.TRANSPARENT)
        }

        // Đặt lại giá trị RangeSlider về mặc định
        startValue = 100000.0F
        endValue = 900000.0F
        slider.values = mutableListOf(startValue, endValue)

        // Đặt lại trạng thái của CheckBox về mặc định (không được chọn)
        cbHanoi.isChecked = false
        cbHCM.isChecked = false
        cbNhaTrang.isChecked = false
        cbDaLat.isChecked = false
        cbHaiPhong.isChecked = false
        cbDaNang.isChecked = false
    }

    private fun getSelectedCities(): String {
        val selectedCitiesList = mutableListOf<String>()

        if (cbHanoi.isChecked) selectedCitiesList.add("Hà Nội")
        if (cbHCM.isChecked) selectedCitiesList.add("Hồ Chí Minh")
        if (cbNhaTrang.isChecked) selectedCitiesList.add("Nha Trang")
        if (cbDaLat.isChecked) selectedCitiesList.add("Đà Lạt")
        if (cbHaiPhong.isChecked) selectedCitiesList.add("Hải Phòng")
        if (cbDaNang.isChecked) selectedCitiesList.add("Đà Nẵng")

        return selectedCitiesList.joinToString(", ")
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyRoom.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, idUser: Int) =
            FilerDetail(idUser).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}