package com.o3dr.android.andwidgets.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.o3dr.android.andwidgets.R

class ViewHolder(val view: View, val textView: TextView) : RecyclerView.ViewHolder(view)

class RecyclerViewAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {
    val items = Array(100, { i -> (i).toString() })

    val layoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.textView?.text = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false)
        val textView = view.findViewById(R.id.text_view) as TextView
        val viewHolder = ViewHolder(view, textView)
        return viewHolder
    }

}