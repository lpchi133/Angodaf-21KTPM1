package com.example.angodafake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private val TAB_TITLE = arrayOf("Sắp tới","Hoàn tất","Đã hủy")
private const val ARG_OBJECT = "title_tab"
private const val IS_AVAILABLE= "have_content"

/**
 * A simple [Fragment] subclass.
 * Use the [MyRoom.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyRoom : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var collectionAdapter: CollectionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        collectionAdapter = CollectionAdapter(this)
        val tabHandler = view.findViewById<TabLayout>(R.id.tab_handler)
        val viewPager = view.findViewById<ViewPager2>(R.id.pager)
        viewPager.adapter = collectionAdapter
        TabLayoutMediator(tabHandler, viewPager) { tab, position ->
            tab.text = TAB_TITLE[position]
        }.attach()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       return inflater.inflate(R.layout.fragment_my_room, container, false)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyRoom.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyRoom().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
class CollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
       val HAVE_CONTENT= arrayOf(true,false,true)

        // Return a NEW fragment instance in createFragment(int).
        val fragment = ObjectFragment()
        fragment.arguments = Bundle().apply {
            // The object is just an integer.
            putInt(ARG_OBJECT, position)
            putBoolean(IS_AVAILABLE, HAVE_CONTENT[position])
        }
        return fragment
    }
}


// Instances of this class are fragments representing a single
// object in the collection.
class ObjectFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val have_content= getBoolean(IS_AVAILABLE)
           return when (getInt(ARG_OBJECT)) {
                0 -> inflater.inflate(if (have_content) R.layout.tab_active_room else R.layout.empty_active_room, container, false)
                1 -> inflater.inflate(if (have_content) R.layout.tab_past_room else R.layout.empty_past_room, container, false)
                else -> { // Note the block
                    inflater.inflate(if (have_content) R.layout.tab_canceled_room else R.layout.empty_canceled_room, container, false)

                }
            }

        }
        return inflater.inflate(R.layout.empty_active_room, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
//            val textView: TextView = view.findViewById<TextView>(R.id.text_no_place)
//            textView.text = getInt(ARG_OBJECT).toString()
//        }
//    }
}