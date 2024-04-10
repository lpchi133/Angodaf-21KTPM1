package com.example.angodafake

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.db.Hotel
import com.example.angodafake.db.HotelDatabase
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home(private var idUser: Int) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var homeLayout: View

    private lateinit var hotelAdapter: List<Hotel>
    private lateinit var adapter: HotelAdapter
    private lateinit var listHotels: List<Hotel>
    private lateinit var hotel_db: HotelDatabase
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
    ): View {
        homeLayout = inflater.inflate(R.layout.fragment_home, container, false)

        setupViews(homeLayout)
        return homeLayout
    }

    private fun setupViews(view: View) {
        hotel_db = HotelDatabase.getInstance(requireContext())
        listHotels = hotel_db.HotelDAO().getHotelList()

        val hotelsRecyclerView = view.findViewById<RecyclerView>(R.id.contactsRV)
        hotelAdapter = ArrayList(listHotels)
        adapter = HotelAdapter(requireContext(), hotelAdapter, idUser)
        hotelsRecyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(requireContext())
        hotelsRecyclerView.layoutManager = layoutManager
        hotelsRecyclerView.setHasFixedSize(true)

        val searchEditText = view.findViewById<EditText>(R.id.nameHotelSearch)
        val findButton = view.findViewById<Button>(R.id.findButton)

        findButton.setOnClickListener {
            val searchText = searchEditText.text.toString()
            val args = Bundle()
            val hotelIds = filterHotels(searchText).map { it.id }.toIntArray()
            args.putIntArray("hotelIds", hotelIds)
            args.putIntArray("saveIds", hotelIds)
            args.putString("searchText", searchText)

            // Khởi tạo Fragment Filter và đính kèm Bundle
            val filterFragment = Filter(idUser)

            filterFragment.arguments = args

            // Thay thế Fragment hiện tại bằng Fragment Filter
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, filterFragment)
                .addToBackStack(null)  // Để quay lại Fragment Home khi ấn nút Back
                .commit()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, idUser: Int) =
            Home(idUser).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun filterHotels(query: String): List<Hotel> {
        val filteredList = mutableListOf<Hotel>()
        for (hotel in listHotels) {
            val hotelNameLower = hotel.name.toLowerCase(Locale.getDefault())
            val locationDetailLower = hotel.locationDetail.toLowerCase(Locale.getDefault())

            // Kiểm tra xem query có tồn tại trong tên khách sạn hoặc chi tiết địa điểm không
            if (hotelNameLower.contains(query.toLowerCase(Locale.getDefault())) ||
                locationDetailLower.contains(query.toLowerCase(Locale.getDefault()))
            ) {
                filteredList.add(hotel)
            }
        }
        return filteredList
    }
}