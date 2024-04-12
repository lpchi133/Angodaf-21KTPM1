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
import com.example.angodafake.db.Hotel
import com.example.angodafake.db.HotelDatabase
import com.example.angodafake.db.Picture
import com.example.angodafake.db.Purchase

class PastCancelPurchaseAdapter(private val context: Context, private var past_cancelPurchase: List<Purchase>) : RecyclerView.Adapter<PastCancelPurchaseAdapter.MyViewHolder>() {
    var onItemClick: ((Purchase) -> Unit)? = null
    private var listener: OnItemClickListener? = null
    private lateinit var hotel_db: HotelDatabase
    private lateinit var HotelMarked: Hotel
    private lateinit var Picture: Picture
    interface OnItemClickListener {
        fun onItemClick(purchase: Purchase)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val past_cancelPurchaseItem = LayoutInflater.from(parent.context).inflate(R.layout.custom_past_cancel_purchase_adapter, parent, false)
        return MyViewHolder(past_cancelPurchaseItem)
    }

    override fun getItemCount(): Int {
        return past_cancelPurchase.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = past_cancelPurchase[position]

        hotel_db = HotelDatabase.getInstance(context)

        HotelMarked = hotel_db.HotelDAO().getHotelByID(currentItem.ID_Hotel)
        Picture = hotel_db.PictureDAO().getPictureByHotelID(currentItem.ID_Hotel)

        val picture = context.resources.getIdentifier(Picture.picture, "drawable", context.packageName)

        holder.timebooking.text = currentItem.time_booking
        holder.idorder.text = currentItem.id.toString()
        if (currentItem.status_purchase == "Đã hoàn tiền") {
            holder.statuspurchase1.text = currentItem.status_purchase
        } else if (currentItem.status_purchase == "Chưa hoàn tiền") {
            holder.statuspurchase2.text = currentItem.status_purchase
        } else {
            holder.statuspurchase3.text = currentItem.status_purchase
        }
        holder.imagehotel.setImageResource(picture)
        holder.namehotel.text = HotelMarked.name
        holder.checkin.text = currentItem.checkIn
        holder.checkout.text = currentItem.checkOut

        holder.reorderbtn.setOnClickListener {
            Toast.makeText(context, "Press", Toast.LENGTH_SHORT).show()
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentItem)
        }
    }

    class MyViewHolder(past_cancelPurchaseItem: View) : RecyclerView.ViewHolder(past_cancelPurchaseItem) {
        val timebooking: TextView = past_cancelPurchaseItem.findViewById(R.id.time_booking)
        val idorder: TextView = past_cancelPurchaseItem.findViewById(R.id.id_order)
        val statuspurchase1: TextView = past_cancelPurchaseItem.findViewById(R.id.status1)
        val statuspurchase2: TextView = past_cancelPurchaseItem.findViewById(R.id.status2)
        val statuspurchase3: TextView = past_cancelPurchaseItem.findViewById(R.id.status3)
        val imagehotel: ImageView = past_cancelPurchaseItem.findViewById(R.id.image_hotel)
        val namehotel: TextView = past_cancelPurchaseItem.findViewById(R.id.name_hotel)
        val checkin: TextView = past_cancelPurchaseItem.findViewById(R.id.check_in)
        val checkout: TextView = past_cancelPurchaseItem.findViewById(R.id.check_out)
        val reorderbtn: Button = past_cancelPurchaseItem.findViewById(R.id.btn_reorder2)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Purchase>) {
        past_cancelPurchase = newList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: PastCancelPurchaseAdapter.OnItemClickListener) {
        this.listener = listener
    }
}