package com.example.angodafake.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.R
import com.example.angodafake.db.Hotel
import com.example.angodafake.db.Picture_Hotel
import com.example.angodafake.db.Purchase

class ActivePurchaseAdapter(private val context: Context, private var activePurchase: List<Purchase>) : RecyclerView.Adapter<ActivePurchaseAdapter.MyViewHolder>() {
    var onItemClick: ((Purchase) -> Unit)? = null
    private var listener: OnItemClickListener? = null
//    private lateinit var hotel_db: HotelDatabase
    private lateinit var HotelMarked: Hotel
    private lateinit var Picture_Hotel: Picture_Hotel
    interface OnItemClickListener {
        fun onItemClick(purchase: Purchase)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val activePurchaseItem = LayoutInflater.from(parent.context).inflate(R.layout.custom_active_purchase_adapter, parent, false)
        return MyViewHolder(activePurchaseItem)
    }

    override fun getItemCount(): Int {
        return activePurchase.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentItem = activePurchase[position]
//
//        hotel_db = HotelDatabase.getInstance(context)
//
//        HotelMarked = hotel_db.HotelDAO().getHotelByID(currentItem.ID_Hotel)
//        Picture = hotel_db.PictureDAO().getPictureByHotelID(currentItem.ID_Hotel)
//
//        val picture = context.resources.getIdentifier(Picture.picture, "drawable", context.packageName)
//
//        holder.timebooking.text = currentItem.time_booking
//        holder.idorder.text = currentItem.id.toString()
//        if (currentItem.status_purchase == "Đã thanh toán") {
//            holder.statusperchase1.text = currentItem.status_purchase
//        } else {
//            holder.statusperchase2.text = currentItem.status_purchase
//        }
//        holder.imagehotel.setImageResource(picture)
//        holder.namehotel.text = HotelMarked.name
//        holder.checkin.text = currentItem.checkIn
//        holder.checkout.text = currentItem.checkOut
//        holder.cancelbtn.setOnClickListener {
//            val intent = Intent(context, CancelPurchase::class.java)
//            context.startActivity(intent)
//        }
//
//        holder.reorderbtn.setOnClickListener {
//            Toast.makeText(context, "Press", Toast.LENGTH_SHORT).show()
//        }
//
//        holder.itemView.setOnClickListener {
//            onItemClick?.invoke(currentItem)
//        }
    }

    class MyViewHolder(activePurchaseItem: View) : RecyclerView.ViewHolder(activePurchaseItem) {
        val timebooking: TextView = activePurchaseItem.findViewById(R.id.time_booking)
        val idorder: TextView = activePurchaseItem.findViewById(R.id.id_order)
        val statusperchase1: TextView = activePurchaseItem.findViewById(R.id.status1)
        val statusperchase2: TextView = activePurchaseItem.findViewById(R.id.status2)
        val imagehotel: ImageView = activePurchaseItem.findViewById(R.id.image_hotel)
        val namehotel: TextView = activePurchaseItem.findViewById(R.id.name_hotel)
        val checkin: TextView = activePurchaseItem.findViewById(R.id.check_in)
        val checkout: TextView = activePurchaseItem.findViewById(R.id.check_out)
        val cancelbtn: Button = activePurchaseItem.findViewById(R.id.btn_removeorder)
        val reorderbtn: Button = activePurchaseItem.findViewById(R.id.btn_reorder1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Purchase>) {
        activePurchase = newList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}