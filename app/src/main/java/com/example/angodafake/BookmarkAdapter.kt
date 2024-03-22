package com.example.angodafake

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.db.BookmarkDatabase
import com.example.angodafake.db.Bookmarks
import com.example.angodafake.db.Hotel
import com.example.angodafake.db.HotelDatabase
import com.example.angodafake.db.Picture
import com.example.angodafake.db.PictureDatabase

class BookmarkAdapter(private val context: Context, private var bookmarks: List<Bookmarks>) : RecyclerView.Adapter<BookmarkAdapter.MyViewHolder>() {
    private var listener: OnItemClickListener? = null
    private lateinit var hotel_db: HotelDatabase
    private lateinit var picture_db: PictureDatabase
    private lateinit var bookmark_db: BookmarkDatabase
    private lateinit var HotelMarked: Hotel
    private lateinit var Picture: Picture

    interface OnItemClickListener {
        fun onItemClick(bookmark: Bookmarks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val bookmarkItem = LayoutInflater.from(parent.context).inflate(R.layout.custom_bookmark, parent, false)
        return MyViewHolder(bookmarkItem)
    }

    override fun getItemCount(): Int {
        return bookmarks.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = bookmarks[position]

        hotel_db = HotelDatabase.getInstance(context)
        HotelMarked = hotel_db.HotelDAO().getHotelByID(currentItem.ID_Hotel)
        picture_db = PictureDatabase.getInstance(context)
        Picture = picture_db.PictureDAO().getPictureByHotelID(currentItem.ID_Hotel)
        bookmark_db = BookmarkDatabase.getInstance(context)

        val idPicture = context.resources.getIdentifier(Picture.picture, "drawable", context.packageName)
        holder.img.setImageResource(idPicture)
        holder.hotelName.text = HotelMarked.name
        holder.buttonFav.setOnClickListener {
            bookmark_db.BookmarkDAO().deleteBookmark(currentItem)
            val bookmarks_tmp = bookmarks.toMutableList()
            bookmarks_tmp.removeAt(position)
            updateList(bookmarks_tmp)
            Toast.makeText(context, "Đã xoá khách sạn", Toast.LENGTH_SHORT).show()
        }
        holder.location.text = HotelMarked.city
        holder.rateStatus.text = when (HotelMarked.point.toInt()){
                                    in 0 until 3 -> { "Cực tệ" }
                                    in 3 until 5 -> { "Tệ" }
                                    in 5 until 6 -> { "Trung bình" }
                                    in 6 until 8 -> { "Tốt" }
                                    in 8 until 9 -> { "Rất tốt" }
                                    else -> { "Tuyệt vời" }
                                }
        holder.Point.text = HotelMarked.point.toString()
    }

    class MyViewHolder(bookmarkItem: View) : RecyclerView.ViewHolder(bookmarkItem) {
        val img: ImageView = bookmarkItem.findViewById(R.id.imageView)
        val hotelName: TextView = bookmarkItem.findViewById(R.id.hotelName)
        val buttonFav: Button = bookmarkItem.findViewById(R.id.fav)
        val location: TextView = bookmarkItem.findViewById(R.id.Location)
        val rateStatus: TextView = bookmarkItem.findViewById(R.id.rateStatus)
        val Point: TextView = bookmarkItem.findViewById(R.id.point)
        val quaCM: TextView = bookmarkItem.findViewById(R.id.quaCM)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Bookmarks>) {
        bookmarks = newList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}