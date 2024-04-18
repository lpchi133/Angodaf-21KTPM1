package com.example.angodafake.Adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.ListRoom
import com.example.angodafake.R
import com.example.angodafake.db.Voucher
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class VoucherAdapter(private val fragment: FragmentActivity, private var voucher: MutableList<Voucher>, private var idUser: String, private var nameHotel: String) : RecyclerView.Adapter<VoucherAdapter.MyViewHolder>() {
    var onItemClick: ((Voucher) -> Unit)? = null
    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(voucher: Voucher)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val voucherItem = LayoutInflater.from(parent.context).inflate(R.layout.custom_voucher_adapter, parent, false)
        return MyViewHolder(voucherItem)
    }

    override fun getItemCount(): Int {
        return voucher.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = voucher[position]

        val max_discount = format1(currentItem.max_discount!!)
        val money_discount = format1(currentItem.money_discount!!)
        val limit_price = format1(currentItem.limit_price!!)

        holder.name.text = format(currentItem.percentage.toString(), max_discount, money_discount)
        holder.limit.text = format2(limit_price)
        holder.quantity.text = currentItem.quantity.toString()
        holder.btnUse.setOnClickListener {
            val arg = Bundle()
            arg.putString("hotelPosition", currentItem.ID_Hotel)
            arg.putString("hotelName", nameHotel)

            val listRoom = ListRoom(idUser)
            listRoom.arguments = arg

            val fragmentManager = fragment.supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, listRoom)
                .addToBackStack(null)
                .commit()
        }
    }

    fun format(percentage: String, max_discount: String, money_discount: String): Spanned? {
        val temp: String = if (money_discount == "0") {
            "Giảm $percentage% Giảm tối đa &#8363;$max_discount"
        } else {
            "Giảm &#8363;$money_discount"
        }

        return Html.fromHtml(temp, Html.FROM_HTML_MODE_COMPACT)
    }

    fun format1(money: Double): String {
        val formatSymbols = DecimalFormatSymbols()
        formatSymbols.groupingSeparator = '.'

        val decimalFormat = DecimalFormat("#,##0", formatSymbols)
        return decimalFormat.format(money)
    }

    fun format2(limit_price: String): Spanned? {
        val temp = "Đơn tối thiểu &#8363;$limit_price"
        return Html.fromHtml(temp, Html.FROM_HTML_MODE_COMPACT)
    }
    class MyViewHolder(voucherItem: View) : RecyclerView.ViewHolder(voucherItem) {
        val name: TextView = voucherItem.findViewById(R.id.voucher_name)
        val limit: TextView = voucherItem.findViewById(R.id.voucher_limited)
        val quantity: TextView = voucherItem.findViewById(R.id.voucher_quantity)
        val btnUse: Button = voucherItem.findViewById(R.id.usenow)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Voucher>) {
        voucher = newList.toMutableList()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: VoucherAdapter.OnItemClickListener) {
        this.listener = listener
    }
}