package com.example.angodafake.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.R
import com.example.angodafake.Utilities.PictureUtils
import com.example.angodafake.Utilities.RoomUtils
import com.example.angodafake.db.Hotel
//import com.example.angodafake.db.HotelDatabase
import com.example.angodafake.db.Picture_Hotel
import com.example.angodafake.db.Rooms
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class HotelAdapter(private val context: Context, private var hotels: List<Hotel>, private var idUser: Int) : RecyclerView.Adapter<HotelAdapter.ViewHolder>() {
    private lateinit var Picture_Hotel: Picture_Hotel
    private var listener: OnItemClickListener? = null
    private lateinit var database: DatabaseReference

    // Interface cho sự kiện click
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val hotelName = listItemView.findViewById<TextView>(R.id.hotelName)
        val City = listItemView.findViewById<TextView>(R.id.City)
        val ratingBar: RatingBar = listItemView.findViewById(R.id.ratingBar)
        val pointView = listItemView.findViewById<TextView>(R.id.point)
        val img: ImageView = listItemView.findViewById(R.id.imageView)
        val rateStatus: TextView = listItemView.findViewById(R.id.rateStatus)
        val count_cmt: TextView = listItemView.findViewById(R.id.cmt)
        val buttonFav: ImageView = listItemView.findViewById(R.id.fav)
        val buttonShare: ImageView = listItemView.findViewById(R.id.shareBtn)
        val price: TextView = listItemView.findViewById(R.id.price)

        init {
            // Thêm sự kiện click cho itemView
            itemView.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val hotelsView = inflater.inflate(R.layout.custom_hotel, parent, false)
        // Return a new holder instance
        return ViewHolder(hotelsView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hotel : Hotel = hotels[position]

        //PictureUtils.getPictureByHotelID(hotel.ID!!){picture ->
            //Picture_Hotel = picture
//            println("Picture ID: ${Picture_Hotel.ID_Hotel}, Type: ${Picture_Hotel.picture}, Price: ${Picture_Hotel.picture_onwer}")

            database = Firebase.database.reference
            val hotelsRef = database.child("hotels")
//
            val idPicture = context.resources.getIdentifier("quang_ba_khach_san", "drawable", context.packageName)
            holder.img.setImageResource(idPicture)
            holder.hotelName.text = hotel.name
            holder.ratingBar.rating = hotel.star!!.toFloat()
            holder.City.text = hotel.city
            holder.pointView.text = hotel.point.toString()
            holder.count_cmt.text = hotel.total_comments.toString() + " nhận xét"
            holder.rateStatus.text = when (hotel.point?.toInt()){
                in 0 until 3 -> { "Cực tệ" }
                in 3 until 5 -> { "Tệ" }
                in 5 until 6 -> { "Trung bình" }
                in 6 until 8 -> { "Tốt" }
                in 8 until 9 -> { "Rất tốt" }
                else -> { "Tuyệt vời" }
            }

            RoomUtils.getRoomByHotelID(hotel.ID!!){ fetchedRoomList   ->
                var roomList: List<Rooms> = emptyList()
                roomList = fetchedRoomList
                val lowestPrice = roomList.minOfOrNull { it.price ?: Int.MAX_VALUE } ?: Int.MAX_VALUE
                holder.price.text = lowestPrice.toString() + " đ"
                Log.d("Adapter", "Room: ${lowestPrice}")

                hotelsRef.child(hotel.ID!!).child("money").setValue(lowestPrice)
            }

        //}

//        // Khởi tạo SharedPreferences
//        val sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        // Lấy trạng thái yêu thích từ SharedPreferences, mặc định là false
//        val isFavourite = sharedPref.getBoolean("isFavourite_${hotel.id}", false)
//        // Thiết lập trạng thái của nút từ SharedPreferences
//        holder.buttonFav.isSelected = isFavourite
//
//        if(hotel_db.BookmarkDAO().checkIfExistHotelId(hotel.id) > 0){
//            val drawableResId = context.resources.getIdentifier("baseline_favorite_24", "drawable", context.packageName)
//            holder.buttonFav.setBackgroundResource(drawableResId)
//        }

//        holder.buttonFav.setOnClickListener {
//            // Đảo ngược trạng thái khi nút được nhấn
//            holder.buttonFav.isSelected = !holder.buttonFav.isSelected
//            // Lưu trạng thái vào SharedPreferences
//            val editor = sharedPref.edit()
//            editor.putBoolean("isFavourite_${hotel.id}", holder.buttonFav.isSelected)
//            editor.apply()
//            if(holder.buttonFav.isSelected){
//                val drawableResId = context.resources.getIdentifier("baseline_favorite_24", "drawable", context.packageName)
//                holder.buttonFav.setBackgroundResource(drawableResId)
//                val saveBookmark = Bookmarks(idUser, hotel.id)
//                hotel_db.BookmarkDAO().insertBookmark(saveBookmark)
//            } else {
//                val drawableResId = context.resources.getIdentifier("baseline_favorite", "drawable", context.packageName)
//                holder.buttonFav.setBackgroundResource(drawableResId)
//                hotel_db.BookmarkDAO().deleteBookmarkByHotelId(hotel.id)
//            }
//        }



//        holder.buttonShare.tag = position
//        holder.buttonShare.setOnClickListener{
//            onShareButtonClick(it)
//        }
    }

    override fun getItemCount(): Int {
        return hotels.size
    }

    fun getStudents(): List<Hotel> {
        return hotels
    }

    fun updateDataGradually(newData: List<Hotel>) {
        hotels = newData
        notifyDataSetChanged()
    }

    fun onShareButtonClick(view: View) {
        val position = view.tag as Int
        val hotel = hotels[position]

        // Tạo một Intent để chia sẻ thông tin về bookmark
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(
            Intent.EXTRA_TEXT, "Check out this hotel: ${hotel.name}\n" +
                "Location: ${hotel.locationDetail}\n" +
                "Description: ${hotel.description}\n" +
                "Rate: ${hotel.point}")

        // Mở activity chia sẻ với Intent đã tạo
        context.startActivity(Intent.createChooser(shareIntent, "Share bookmark via"))
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
