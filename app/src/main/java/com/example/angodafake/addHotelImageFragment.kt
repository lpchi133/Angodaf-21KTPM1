package com.example.angodafake

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.angodafake.Adapter.GridAdapter
import com.example.angodafake.Utilities.PictureUtils
import com.example.angodafake.db.Picture_Hotel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.database.database

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addHotelImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class addHotelImageFragment(private var idUser: String) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var fab: FloatingActionButton
    private lateinit var gridView: GridView
    private lateinit var adapter: GridAdapter

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
        val view = inflater.inflate(R.layout.fragment_add_hotel_image, container, false)

        fab = view.findViewById(R.id.floatingActionButton)
        gridView = view.findViewById(R.id.gridView)

        var picList = ArrayList<Picture_Hotel>()
        adapter = GridAdapter(requireContext(), picList)
        gridView.adapter = adapter

        PictureUtils.getHotelPicturesList("1"){
            picList.clear()
            picList.addAll(it)
            Log.d("picList", picList.toString())
            adapter.notifyDataSetChanged()
        }



        fab.setOnClickListener{
            val mainActivity = requireActivity() as MainActivity
            mainActivity.replaceFragment(UploadImageFragment(idUser))
        }
        return view
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment addHotelImageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, idUser: String) =
            addHotelImageFragment(idUser).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}