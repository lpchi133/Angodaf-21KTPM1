package com.example.angodafake.Adapter

import android.content.Context
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
        fun onCountRoomClick(position: Int)
    }

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val roomName = listItemView.findViewById<TextView>(R.id.roomName)
        val countBed = listItemView.findViewById<TextView>(R.id.countBed)
        val count = listItemView.findViewById<TextView>(R.id.count)
        val img: ImageView = listItemView.findViewById(R.id.imageView)
        val convenience: TextView = listItemView.findViewById(R.id.convenience)
        val countRoom: TextView = listItemView.findViewById(R.id.countRoom)
        val price_room: TextView = listItemView.findViewById(R.id.price_room)
        val count_Room: TextView = listItemView.findViewById(R.id.count_Room)


        init {
            // Thêm sự kiện click cho itemView
            itemView.setOnClickListener {

                listener?.onItemClick(adapterPosition)
            }

            // Thêm sự kiện click cho countRoom
            countRoom.setOnClickListener {
                listener?.onCountRoomClick(adapterPosition)
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

        val idPicture = context.resources.getIdentifier(room.picture, "drawable", context.packageName)
        holder.img.setImageResource(idPicture)
        holder.roomName.text = room.type
//        holder.countBed.text = room.bedQuantity.toString() + " giường đôi"
//        holder.count.text = "Tối đa " + (room.bedQuantity?.times(2)).toString() + " người"
//        if(intArray[position] <= room.available!!) {
//            holder.countRoom.text = "Số phòng:         " + intArray[position].toString() + "   +"
//        }
//        else{
//            holder.countRoom.text = "Số phòng:         " + room.available.toString() + "   +"
//        }
//        holder.convenience.text = room.benefit
//        holder.price_room.text = room.price.toString() + " đ"
//        if(room.available == 0){
//            holder.count_Room.text = "Hết phòng rồi ní ơi!"
//        }
//        else {
//            holder.count_Room.text = room.available.toString() + " phòng cuối cùng!"
//        }
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
