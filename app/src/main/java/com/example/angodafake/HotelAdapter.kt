package com.example.angodafake

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.Utilities.PictureUtils
import com.example.angodafake.Utilities.RoomUtils
import com.example.angodafake.db.Hotel
//import com.example.angodafake.db.HotelDatabase
import com.example.angodafake.db.Picture
import com.example.angodafake.db.Rooms
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class HotelAdapter(private val context: Context, private var hotels: List<Hotel>, private var idUser: Int) : RecyclerView.Adapter<HotelAdapter.ViewHolder>() {
    private lateinit var Picture: Picture
    private var listener: HotelAdapter.OnItemClickListener? = null
    private lateinit var database: DatabaseReference
    // Interface cho sự kiện click
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val nameTextView = listItemView.findViewById<TextView>(R.id.hotelName)
        val locationTextView = listItemView.findViewById<TextView>(R.id.Location)
        val pointView = listItemView.findViewById<TextView>(R.id.point)
        val img: ImageView = listItemView.findViewById(R.id.imageView)
        val rateStatus: TextView = listItemView.findViewById(R.id.rateStatus)
        val buttonFav: ImageView = listItemView.findViewById(R.id.fav)
        val buttonShare: ImageView = listItemView.findViewById(R.id.shareBtn)
        val price_room: TextView = listItemView.findViewById(R.id.price_room)

        init {
            // Thêm sự kiện click cho itemView
            itemView.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelAdapter.ViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val hotelsView = inflater.inflate(R.layout.custom_hotel, parent, false)
        // Return a new holder instance
        return ViewHolder(hotelsView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hotel : Hotel = hotels[position]

        database = Firebase.database.reference

        PictureUtils.getPictureByHotelID(hotel.ID!!){picture ->
            Picture = picture
            println("Picture ID: ${Picture.ID_Hotel}, Type: ${Picture.picture}, Price: ${Picture.picture_onwer}")

            val idPicture = context.resources.getIdentifier(Picture.picture, "drawable", context.packageName)
            holder.img.setImageResource(idPicture)
            holder.nameTextView.text = hotel.name
            holder.locationTextView.text = hotel.locationDetail
            holder.pointView.text = hotel.point.toString()

        }
        var roomList: List<Rooms> = emptyList()
        RoomUtils.getRoomByHotelID(hotel.ID!!){ fetchedRoomList   ->
            roomList = fetchedRoomList
            roomList.forEach { room ->
                println("Room ID: ${room.ID_Hotel}, Type: ${room.type}, Price: ${room.price}")
            }
            val lowestPrice = roomList.minOfOrNull { it.price ?: Double.MAX_VALUE } ?: Double.MAX_VALUE
            Log.d("lowestPrice", lowestPrice.toString())
            holder.price_room.text = lowestPrice.toString() + " đ"
        }




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

        holder.rateStatus.text = when (hotel.point?.toInt()){
            in 0 until 3 -> { "Cực tệ" }
            in 3 until 5 -> { "Tệ" }
            in 5 until 6 -> { "Trung bình" }
            in 6 until 8 -> { "Tốt" }
            in 8 until 9 -> { "Rất tốt" }
            else -> { "Tuyệt vời" }
        }

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
