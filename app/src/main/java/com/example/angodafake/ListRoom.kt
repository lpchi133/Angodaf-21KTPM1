package com.example.angodafake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.db.Hotel
import com.example.angodafake.db.HotelDatabase
import com.example.angodafake.db.Rooms

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [ListRoom.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListRoom : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var hotel_db: HotelDatabase
    private lateinit var roomAdapter: List<Rooms>
    private lateinit var adapter: RoomAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        hotel_db = HotelDatabase.getInstance(requireContext())
        var view =  inflater.inflate(R.layout.fragment_room, container, false)
        val nameTextView = view.findViewById<TextView>(R.id.nameHotelSearch)
        val args = arguments
        val itemPosition = args?.getInt("hotelPosition") ?: -1
        val searchText = args?.getString("hotelName")
        nameTextView.text = searchText
        val rooms = hotel_db.RoomDAO().getRoomsByHotelID(itemPosition)

        val roomsRecyclerView = view.findViewById<RecyclerView>(R.id.contactsRV)
        roomAdapter = ArrayList(rooms)
        adapter = RoomAdapter(requireContext(), roomAdapter)
        roomsRecyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(requireContext())
        roomsRecyclerView.layoutManager = layoutManager
        roomsRecyclerView.setHasFixedSize(true)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyProfile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListRoom().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}