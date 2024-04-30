package com.example.angodafake

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.ImageButton
import android.widget.ProgressBar
import com.example.angodafake.Adapter.GridAdapter
import com.example.angodafake.Utilities.HotelUtils
import com.example.angodafake.Utilities.PictureUtils
import com.example.angodafake.Utilities.RoomUtils
import com.example.angodafake.db.Hotel
import com.example.angodafake.db.Rooms
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddRoomImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRoomImageFragment(private var idHotel: String) : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var btn_back: ImageButton
    private lateinit var fab: FloatingActionButton
    private lateinit var progressBar1: ProgressBar
    private lateinit var btn_add: Button
    private lateinit var gridView: GridView
    private lateinit var adapter: GridAdapter
    private lateinit var picList : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_room_image, container, false)
        initUI(view)

        adapter = GridAdapter(requireContext(), picList)
        gridView.adapter = adapter

        btn_back.setOnClickListener {
            prevStepWithData()
        }

        fab.setOnClickListener{
            nextStepWithData()
        }

        progressBar1.setOnClickListener {
            prevStepWithData()
        }

        btn_add.setOnClickListener {
            if (picList.size != 0){
                val ID_Hotel = idHotel
                val quantity = arguments?.getString("quantity")!!.toInt()
                val type = arguments?.getString("roomType")
                val acreage = arguments?.getString("acreage")!!.toDouble()
                val direction = arguments?.getString("direction")
                val benefit = arguments?.getString("benefit")
                val price = arguments?.getString("price")!!.toInt()
                val single_bed =  arguments?.getString("singleBed")!!.toInt()
                val double_bed = arguments?.getString("doubleBed")!!.toInt()
                val capacity = single_bed + double_bed * 2
                val room = Rooms(null, ID_Hotel, quantity, 0, type, acreage, direction, benefit, price, single_bed, double_bed, capacity)

                RoomUtils.addRoom(room, idHotel){
                    PictureUtils.addRoomPictures(idHotel, it, picList)
                    val mainActivity = requireActivity() as MainActivity
                    mainActivity.replaceFragment(MyHotel())
                    showSuccessSnackBar("Thêm phòng thành công.", view)
                }
            } else{
                showSnackBar("Thêm ít nhất 1 ảnh của phòng khách sạn.", view)
            }

        }

        return view
    }

    private fun prevStepWithData(){
        val arg = Bundle()

        arg.putString("roomType", arguments?.getString("roomType"))
        arg.putString("acreage", arguments?.getString("acreage"))
        arg.putString("direction", arguments?.getString("direction"))
        arg.putString("benefit", arguments?.getString("benefit"))
        arg.putString("singleBed", arguments?.getString("singleBed"))
        arg.putString("doubleBed", arguments?.getString("doubleBed"))
        arg.putString("price", arguments?.getString("price"))
        arg.putString("quantity", arguments?.getString("quantity"))

        arg.putStringArrayList("pics", picList)

        val addRoomFragment = AddRoomFragment(idHotel)
        addRoomFragment.arguments = arg

        val mainActivity = requireActivity() as MainActivity
        mainActivity.replaceFragment(addRoomFragment)
    }
    private fun nextStepWithData(){
        val arg = Bundle()

        arg.putString("roomType", arguments?.getString("roomType"))
        arg.putString("acreage", arguments?.getString("acreage"))
        arg.putString("direction", arguments?.getString("direction"))
        arg.putString("benefit", arguments?.getString("benefit"))
        arg.putString("singleBed", arguments?.getString("singleBed"))
        arg.putString("doubleBed", arguments?.getString("doubleBed"))
        arg.putString("price", arguments?.getString("price"))
        arg.putString("quantity", arguments?.getString("quantity"))

        arg.putStringArrayList("pics", picList)

        val uploadRoomImageFragment = UploadRoomImageFragment(idHotel)
        uploadRoomImageFragment.arguments = arg

        val mainActivity = requireActivity() as MainActivity
        mainActivity.replaceFragment(uploadRoomImageFragment)
    }

    private fun showSnackBar(msg: String, view: View) {
        val snackbar = Snackbar.make(view.rootView, msg, Snackbar.LENGTH_LONG)
        // Đổi màu background của Snackbar
        snackbar.view.backgroundTintList = ColorStateList.valueOf(Color.RED)
        snackbar.setTextColor(Color.WHITE)
        snackbar.show()
    }
    private fun showSuccessSnackBar(msg: String, view: View) {
        val snackbar = Snackbar.make(view.rootView, msg, Snackbar.LENGTH_LONG)
        snackbar.view.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3193FF"))
        snackbar.setTextColor(Color.WHITE)
        snackbar.show()
    }

    private fun initUI(view: View){
        fab = view.findViewById(R.id.floatingActionButton)
        btn_back = view.findViewById(R.id.btn_back)
        progressBar1 = view.findViewById(R.id.progressBar1)
        btn_add = view.findViewById(R.id.btn_add)

        gridView = view.findViewById(R.id.gridView)

        picList = ArrayList()
        val tmp = arguments?.getStringArrayList("pics")
        if (tmp != null){
            picList.addAll(tmp)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddRoomImageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(idHotel: String) =
            AddRoomImageFragment(idHotel).apply {
                arguments = Bundle().apply {
                }
            }
    }
}