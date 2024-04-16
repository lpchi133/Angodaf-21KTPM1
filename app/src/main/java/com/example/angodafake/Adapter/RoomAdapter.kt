package com.example.angodafake.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.R
import com.example.angodafake.db.Rooms

class RoomAdapter(private val context: Context, private var rooms: List<Rooms>, private var intArray: IntArray) : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {
    //private lateinit var Picture: Picture
    private var listener: OnItemClickListener? = null
    // Interface cho sự kiện click
    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onPlusClick(position: Int)
        fun onMinusClick(position: Int)
        fun onBookRoomClick(position: Int)
    }

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val roomName = listItemView.findViewById<TextView>(R.id.roomName)
        val count = listItemView.findViewById<TextView>(R.id.count)
        val countBedSingle = listItemView.findViewById<TextView>(R.id.countBedSingle)
        val countBedDouble = listItemView.findViewById<TextView>(R.id.countBedDouble)
        val img: ImageView = listItemView.findViewById(R.id.imageView)
        val convenience: TextView = listItemView.findViewById(R.id.convenience)
        val countRoom: TextView = listItemView.findViewById(R.id.countRoom)
        val price: TextView = listItemView.findViewById(R.id.price)
        val count_Room: TextView = listItemView.findViewById(R.id.count_Room)
        val firstRectangle: TextView = listItemView.findViewById(R.id.firstRectangle)
        val direction: TextView = listItemView.findViewById(R.id.direction)
        val minus: ImageView = listItemView.findViewById(R.id.minus)
        val plus: ImageView = listItemView.findViewById(R.id.plus)
        val bookRoomBtn: TextView = listItemView.findViewById(R.id.buttonSet)


        init {
            // Thêm sự kiện click cho itemView
            itemView.setOnClickListener {

                listener?.onItemClick(adapterPosition)
            }

            // Thêm sự kiện click cho countRoom
            plus.setOnClickListener {
                listener?.onPlusClick(adapterPosition)
            }
            minus.setOnClickListener {
                listener?.onMinusClick(adapterPosition)
            }

            bookRoomBtn.setOnClickListener {
                listener?.onBookRoomClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val roomsView = inflater.inflate(R.layout.custom_room, parent, false)
        // Return a new holder instance
        return ViewHolder(roomsView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room : Rooms = rooms[position]
        holder.roomName.text = room.type
        holder.count.text = "Tối đa " + room.capacity.toString() + " người - " + room.acreage.toString() + " m\u00B2"
        holder.countBedSingle.text = room.single_bed.toString() + " giường đơn   -"
        holder.countBedDouble.text = room.double_bed.toString() + " giường đôi"
        if(room.quantity == 0){
            holder.count_Room.text = "Hết phòng rồi ní ơi!"
        }
        else {
            holder.count_Room.text = room.quantity.toString() + " phòng cuối cùng!"
        }

        holder.direction.text = room.direction.toString()
        holder.price.text = room.price.toString() + " đ"
        if(room.quantity == 0) {
            holder.firstRectangle.text = "HẾT PHÒNG"
        }
        else{
            holder.firstRectangle.text = "CÒN PHÒNG"
        }

        val conveniences = room.benefit?.split("\\")
        val formattedconveniences = conveniences?.map { "❇\uFE0F    $it" }
        val formattedconvenience = formattedconveniences?.joinToString("\n")
        holder.convenience.text = formattedconvenience
        if(intArray[position] <= room.quantity!!) {
            holder.countRoom.text = "Số phòng: " + intArray[position].toString()
        }
        else{
            holder.countRoom.text = "Số phòng: " + room.quantity.toString()
            intArray[position] = room.quantity!!
        }
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    fun updateDataGradually(newData: List<Rooms>) {
        rooms = newData
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}
