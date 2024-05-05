package com.example.angodafake

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.Adapter.BillAdapter
import com.example.angodafake.Utilities.PurchaseUtils
import com.example.angodafake.db.Purchase

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fromFrag = it.getString("from")
        }
    }

    @SuppressLint("NotifyDataSetChanged")
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
            for (bill in list!!){
                PurchaseUtils.getPurchaseByID(bill){
                    bill_list.add(it)
                    adapter.notifyDataSetChanged()
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

    @SuppressLint("NotifyDataSetChanged")
    private fun initUI(view: View){
        recyclerView = view.findViewById(R.id.recyclerView)
        btn_back = view.findViewById(R.id.btn_back)
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