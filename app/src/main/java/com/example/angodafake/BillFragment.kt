package com.example.angodafake

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.Adapter.BillAdapter
import com.example.angodafake.Utilities.PurchaseUtils
import com.example.angodafake.db.Purchase
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [BillFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BillFragment(private var idUser: String) : Fragment() {
    // TODO: Rename and change types of parameters
    private var fromFrag: String? = null
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : BillAdapter
    private lateinit var bill_list : ArrayList<Purchase>
    private lateinit var btn_back : ImageButton
    private lateinit var tv_total : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fromFrag = it.getString("from")
        }
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bill, container, false)
        initUI(view)

        adapter = BillAdapter(requireContext(), bill_list)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        if (fromFrag == "edit" || fromFrag == "edit_room"){
            val list = arguments?.getStringArrayList("bills")
            var total = 0.0
            for (bill in list!!){
                PurchaseUtils.getPurchaseByID(bill){
                    bill_list.add(it)
                    adapter.notifyDataSetChanged()
                    if (it.time_cancel == "" || it.time_cancel == null){
                        total += it.total_purchase!!
                        tv_total.text = "${formatMoney(total.toInt())} VND"
                    }
                }
            }
        }

        btn_back.setOnClickListener {
            if (fromFrag == "edit"){
                val arg = Bundle()
                arg.putString("date", arguments?.getString("date"))

                val hotelManageFrg = MyHotel(idUser)
                hotelManageFrg.arguments = arg

                val mainActivity = requireActivity() as MainActivity
                mainActivity.replaceFragment(hotelManageFrg)
            } else if (fromFrag == "edit_room"){
                val arg = Bundle()
                arg.putString("date", arguments?.getString("date"))
                arg.putString("idHotel", arguments?.getString("idHotel"))

                val roomManageFrg = ManageRoomsFragment(idUser)
                roomManageFrg.arguments = arg

                val mainActivity = requireActivity() as MainActivity
                mainActivity.replaceFragment(roomManageFrg)
            }
        }

        return view
    }

    fun formatMoney(amount: Int): String {
        val formatter = NumberFormat.getNumberInstance(Locale.getDefault()) as DecimalFormat
        formatter.applyPattern("#,###")
        return formatter.format(amount.toLong())
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUI(view: View){
        recyclerView = view.findViewById(R.id.recyclerView)
        btn_back = view.findViewById(R.id.btn_back)
        tv_total = view.findViewById(R.id.tv_total)
        bill_list = ArrayList()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BillFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(fromFrag: String, idUser: String) =
            BillFragment(idUser).apply {
                arguments = Bundle().apply {
                    putString("from", fromFrag)
                }
            }
    }

}