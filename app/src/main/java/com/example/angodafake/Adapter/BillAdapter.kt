package com.example.angodafake.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.R
import com.example.angodafake.Utilities.HotelUtils
import com.example.angodafake.Utilities.RoomUtils
import com.example.angodafake.Utilities.UserUtils
import com.example.angodafake.db.Purchase
class BillAdapter(private val context: Context, private var purchaseList: ArrayList<Purchase>) : RecyclerView.Adapter<BillAdapter.ViewHolder>() {
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val idPurchase = listItemView.findViewById<TextView>(R.id.idPurchase)
        val tv_hotelName = listItemView.findViewById<TextView>(R.id.tv_hotelName)
        val tv_roomType = listItemView.findViewById<TextView>(R.id.tv_roomType)
        val tv_quantity = listItemView.findViewById<TextView>(R.id.tv_quantity)
        val tv_date = listItemView.findViewById<TextView>(R.id.tv_date)
        val tv_customerName = listItemView.findViewById<TextView>(R.id.tv_customerName)
        val tv_contactInf = listItemView.findViewById<TextView>(R.id.tv_contactInf)
        val tv_orderTime = listItemView.findViewById<TextView>(R.id.tv_orderTime)
        val tv_method = listItemView.findViewById<TextView>(R.id.tv_method)
        val tv_status = listItemView.findViewById<TextView>(R.id.tv_status)
        val tv_totalPrice = listItemView.findViewById<TextView>(R.id.tv_totalPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.custom_bill_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }
    override fun getItemCount(): Int {
        return purchaseList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val purchase = purchaseList[position]
        holder.idPurchase.text = purchase.ID
        HotelUtils.getHotelByID(purchase.ID_Hotel!!){
            holder.tv_hotelName.text = it.name
        }
        RoomUtils.getRoomByID(purchase.ID_Hotel!!, purchase.ID_Room!!){
            holder.tv_roomType.text = it.type
        }
        holder.tv_quantity.text = "x$purchase.quantity"
        holder.tv_date.text = "${purchase.date_come} - ${purchase.date_go}"
        UserUtils.getUserByID(purchase.ID_Owner!!){
            holder.tv_customerName.text = it.name
            if (it.phoneN != "" && it.phoneN != null){
                holder.tv_contactInf.text = it.phoneN
            }else{
                holder.tv_contactInf.text = it.email
            }
        }
        holder.tv_orderTime.text = purchase.time_booking
        holder.tv_method.text = purchase.method
        if (purchase.time_cancel == null || purchase.time_cancel == ""){
            holder.tv_status.text = "Thành công"
            holder.tv_status.setTextColor(Color.GREEN)
        } else{
            holder.tv_status.text = "Đã hủy - ${purchase.status_purchase}"
            holder.tv_status.setTextColor(Color.RED)
        }
        holder.tv_totalPrice.text = purchase.total_purchase.toString()
    }
}