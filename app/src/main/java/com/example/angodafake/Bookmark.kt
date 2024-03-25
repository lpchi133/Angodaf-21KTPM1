package com.example.angodafake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.angodafake.db.HotelDatabase
import org.jetbrains.annotations.TestOnly

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Bookmark.newInstance] factory method to
 * create an instance of this fragment.
 */
class Bookmark : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var hotel_db: HotelDatabase
    private lateinit var bookmarksRecyclerView : RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var linearAdapter: BookmarkAdapter

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
        val view = inflater.inflate(R.layout.fragment_bookmark, container, false)

        hotel_db = HotelDatabase.getInstance(requireContext())
        val allBookmarks = hotel_db.BookmarkDAO().getBookmarksByUserID(1)

        bookmarksRecyclerView = view.findViewById(R.id.contactsRV)
        layoutManager = LinearLayoutManager(requireContext())
        bookmarksRecyclerView.layoutManager = layoutManager
        bookmarksRecyclerView.setHasFixedSize(true)

        linearAdapter = BookmarkAdapter(requireContext(), allBookmarks)
        bookmarksRecyclerView.adapter = linearAdapter

        return view
    }

    @TestOnly
    private fun testPrintAllBookmarks() {
        val list = hotel_db.BookmarkDAO().getBookmarkList()
        for (bookmark in list) {
            println(bookmark.toString())
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Bookmark.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Bookmark().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}