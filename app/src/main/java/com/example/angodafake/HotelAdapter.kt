package com.example.angodafake

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.db.Hotel
import com.example.angodafake.db.HotelDatabase
import com.example.angodafake.db.Picture

class HotelAdapter(private val context: Context, private val fragmentManager: FragmentManager, private var hotels: List<Hotel>) : RecyclerView.Adapter<HotelAdapter.ViewHolder>() {
    private lateinit var hotel_db: HotelDatabase
    //private lateinit var Picture: Picture
    private var listener: HotelAdapter.OnItemClickListener? = null
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
        val quaCM: TextView = listItemView.findViewById(R.id.quaCM)
        val convenience: TextView = listItemView.findViewById(R.id.convenience)
        val price_room: TextView = listItemView.findViewById(R.id.price_room)

        init {
            // Thêm sự kiện click cho itemView
            itemView.setOnClickListener {
                val args = Bundle()
                val fragment = Hotel_infor()
                fragment.arguments = args

                fragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .addToBackStack(null)
                    .commit()
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

        hotel_db = HotelDatabase.getInstance(context)
        var Picture = hotel_db.PictureDAO().getPictureByHotelID(hotel.id)
        val rooms = hotel_db.RoomDAO().getRoomsByHotelID(hotel.id)
        val lowestPrice = rooms.minByOrNull { it.price }?.price ?: Double.MAX_VALUE

        val idPicture = context.resources.getIdentifier(Picture.picture, "drawable", context.packageName)
        holder.img.setImageResource(idPicture)
        holder.nameTextView.text = hotel.name
        holder.locationTextView.text = hotel.locationDetail
        holder.pointView.text = hotel.point.toString()
        holder.quaCM.text = hotel.description
        holder.convenience.text = hotel.convenience
        holder.price_room.text = lowestPrice.toString() + " đ"

        holder.rateStatus.text = when (hotel.point.toInt()){
            in 0 until 3 -> { "Cực tệ" }
            in 3 until 5 -> { "Tệ" }
            in 5 until 6 -> { "Trung bình" }
            in 6 until 8 -> { "Tốt" }
            in 8 until 9 -> { "Rất tốt" }
            else -> { "Tuyệt vời" }
        }
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


}
