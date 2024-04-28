package com.example.angodafake.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.angodafake.R
import com.example.angodafake.db.Picture_Hotel
import com.squareup.picasso.Picasso

class GridAdapter(private var context: Context, private var imgList: ArrayList<Picture_Hotel>) : BaseAdapter() {
    private class ViewHolder(row: View?){
        var pic: ImageView? = null

        init{
            pic = row?.findViewById(R.id.gridImage)
        }
    }

    override fun getCount(): Int {
        return imgList.size
    }

    override fun getItem(position: Int): Any {
        return imgList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.grid_item, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val pic = imgList[position]
        Picasso.get().load(pic.picture)
            .into(viewHolder.pic!!)

        return view
    }

}