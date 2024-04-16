package com.example.angodafake.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.CancelPurchase
import com.example.angodafake.PurchaseExtra
import com.example.angodafake.R
import com.example.angodafake.db.Purchase
import com.squareup.picasso.Picasso

class ActivePurchaseAdapter(private val context: Context, private var activePurchase: MutableList<PurchaseExtra>) : RecyclerView.Adapter<ActivePurchaseAdapter.MyViewHolder>() {
    var onItemClick: ((PurchaseExtra) -> Unit)? = null
    private var listener: OnItemClickListener? = null

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

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = activePurchase[position]

        holder.namehotel.text = currentItem.nameHotel
        Picasso.get().load(currentItem.imageHotel).into(holder.imagehotel)
        holder.timebooking.text = currentItem.Purchase?.time_booking
        holder.idorder.text = currentItem.Purchase?.ID.toString()

        if (currentItem.Purchase?.status_purchase == "DA_THANH_TOAN") {
            holder.statusperchase1.text = "Đã thanh toán"
        } else {
            holder.statusperchase2.text = "Chưa thanh toán"
        }

        holder.checkin.text = currentItem.Purchase?.date_come
        holder.checkout.text = currentItem.Purchase?.date_go

        holder.cancelbtn.setOnClickListener {
            val intent = Intent(context, CancelPurchase::class.java)
            context.startActivity(intent)
        }

        holder.reorderbtn.setOnClickListener {
            Toast.makeText(context, "Press", Toast.LENGTH_SHORT).show()
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentItem)
        }
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
    fun updateList(newList: List<PurchaseExtra>) {
        activePurchase = newList.toMutableList()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: ActivePurchaseAdapter.OnItemClickListener) {
        this.listener = listener
    }
}