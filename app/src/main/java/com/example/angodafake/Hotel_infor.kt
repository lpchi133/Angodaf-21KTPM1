package com.example.angodafake

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.angodafake.Utilities.HotelUtils
import com.example.angodafake.Utilities.PictureUtils
import com.example.angodafake.Utilities.RoomUtils
import com.example.angodafake.Utilities.UserUtils
import com.example.angodafake.db.Hotel
import com.example.angodafake.db.Picture_Hotel
import com.example.angodafake.db.Rooms
import com.example.angodafake.db.User


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Hotel_infor.newInstance] factory method to
 * create an instance of this fragment.
 */
class Hotel_infor(private var idUser: Int) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var Picture_Hotel: Picture_Hotel
    private lateinit var User: User
    private lateinit var hotel: Hotel
    private lateinit var popupWindow: PopupWindow

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
        val view = inflater.inflate(R.layout.custom_hotel_detail, container, false)
        val args = arguments
        val itemPosition = args?.getString("hotelPosition")
        val hotelIds = args?.getStringArray("hotelIds")
        val saveIds = args?.getStringArray("saveIds")
        val searchText = args?.getString("searchText")

        val nameTextView = view.findViewById<TextView>(R.id.hotel_name)
        val locationTextView = view.findViewById<TextView>(R.id.address_hotel)
        val pointView = view.findViewById<TextView>(R.id.point)
        val img: ImageView = view.findViewById(R.id.hotel_image)
        val rateStatus: TextView = view.findViewById(R.id.rateStatus)
        val description: TextView = view.findViewById(R.id.description)
        val convenience: TextView = view.findViewById(R.id.convenience)
        val highlight: TextView = view.findViewById(R.id.highlight)
        val price_room: TextView = view.findViewById(R.id.price)
        val nameOwner: TextView = view.findViewById(R.id.nameOwner)
        val hotel_phone: TextView = view.findViewById(R.id.hotel_phone)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)
        val checkIn: TextView = view.findViewById(R.id.time_In)
        val checkOut: TextView = view.findViewById(R.id.time_Out)
        val imgAvt: ImageView = view.findViewById(R.id.avt)
        val count_cmt: TextView = view.findViewById(R.id.cmt)
        val showDetail: TextView = view.findViewById(R.id.showDetail)
        val firstRectangle: TextView = view.findViewById(R.id.firstRectangle)




        if (itemPosition != null) {
            HotelUtils.getHotelByID(itemPosition) { ho ->
                hotel = ho

//                PictureUtils.getPictureByHotelID(hotel.ID!!) { picture ->
//                    Picture_Hotel = picture
//                    val idPicture = requireContext().resources.getIdentifier(Picture_Hotel.picture, "drawable", requireContext().packageName)
////                    val idAvt = requireContext().resources.getIdentifier(Picture_Hotel.picture_onwer, "drawable", requireContext().packageName)
//                    img.setImageResource(idPicture)
//                    imgAvt.setImageResource(idAvt)



                    price_room.text = hotel.money.toString() + " đ"

                    UserUtils.getUserByID(hotel.ID_Owner!!){user ->
                        User = user

                        Log.d("FilterFragment", "Received data - user: $User")


                        nameTextView.text = hotel.name
                        locationTextView.text = hotel.locationDetail
                        pointView.text = hotel.point.toString()
                        //description.text = hotel.description
                        //convenience.text = hotel.conveniences
                        checkIn.text = hotel.checkIn
                        checkOut.text = hotel.checkOut
                        nameOwner.text = User?.name
                        ratingBar.rating = hotel.star!!.toFloat()
                        hotel_phone.text = hotel.phoneNumber
                        count_cmt.text = hotel.total_comments.toString() + " nhận xét"

                        val conveniences = hotel.highlight?.split("\\")
                        val formattedconveniences = conveniences?.map { "✅    $it" }
                        val formattedconvenience = formattedconveniences?.joinToString("\n")
                        convenience.text = formattedconvenience

                        val highlights = hotel.highlight?.split("\\")
                        val formattedHighlights = highlights?.map { "\uD83D\uDCA0    $it" }
                        val formattedHighlight = formattedHighlights?.joinToString("\n")
                        highlight.text = formattedHighlight

                        rateStatus.text = when (hotel.point!!.toInt()){
                            in 0 until 3 -> { "Cực tệ" }
                            in 3 until 5 -> { "Tệ" }
                            in 5 until 6 -> { "Trung bình" }
                            in 6 until 8 -> { "Tốt" }
                            in 8 until 9 -> { "Rất tốt" }
                            else -> { "Tuyệt vời" }
                        }

                        val fullDescription = hotel.description
                        val initialLinesToShow = 3
                        val lines = fullDescription?.split("\\n")
                        val initialDescription = lines?.take(initialLinesToShow)?.joinToString("\n")
                        description.text = initialDescription

                        view.findViewById<ImageView>(R.id.imageView4).setOnClickListener {
                            val arg = Bundle()
                            arg.putStringArray("hotelIds", hotelIds)
                            arg.putStringArray("saveIds", saveIds)
                            arg.putString("searchText", searchText)

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

                        view.findViewById<Button>(R.id.watchRoom).setOnClickListener {
                            val arg = Bundle()
                            arg.putString("hotelPosition", itemPosition)
                            arg.putString("searchText", searchText)
                            arg.putStringArray("hotelIds", hotelIds)
                            arg.putStringArray("saveIds", saveIds)
                            arg.putString("hotelName", hotel.name)

                            val listRoom = ListRoom(idUser)
                            listRoom.arguments = arg

                            val fragmentManager = requireActivity().supportFragmentManager
                            fragmentManager.beginTransaction()
                                .replace(R.id.frameLayout, listRoom)
                                .addToBackStack(null)
                                .commit()
                        }
                        showDetail.setOnClickListener {
                            showPopup()
                        }
                    }


            }
        }
        return view
    }

    private fun showPopup() {
        val inflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_description, null)

        // Đặt alpha cho layout gốc để làm mờ
        val rootView = requireActivity().window.decorView.findViewById<View>(android.R.id.content)
        rootView.alpha = 0.5f

        // Khởi tạo PopupWindow
        popupWindow = PopupWindow(
            popupView,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            true
        )

        // Thiết lập animation và hiển thị PopupWindow từ dưới lên
        popupWindow.animationStyle = R.style.PopupAnimation
        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0)

        // Xử lý sự kiện khi nhấn vào nút hoặc các thành phần trong PopupWindow
        val closeButton = popupView.findViewById<ImageButton>(R.id.btn_close)
        closeButton.setOnClickListener {
            rootView.alpha = 1.0f
            popupWindow.dismiss() // Đóng PopupWindow khi nhấn nút đóng

        }

        val name = popupView.findViewById<TextView>(R.id.name)
        val detail = popupView.findViewById<TextView>(R.id.detail)
        name.text = hotel.name
        detail.text = hotel.description


        popupWindow.setOnDismissListener {
            // Xử lý khi PopupWindow bị đóng
            rootView.alpha = 1.0f
        }
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
        fun newInstance(param1: String, param2: String, idUser: Int) =
            Hotel_infor(idUser).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}