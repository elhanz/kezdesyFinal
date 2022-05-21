package com.example.kezdesy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kezdesy.R
import com.example.kezdesy.model.Room
import kotlinx.android.synthetic.main.row_layout.view.*

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private var myList = emptyList<Room>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.header_txt.text = myList[position].header
        holder.itemView.description_txt.text = myList[position].description
        holder.itemView.minAge_txt.text = myList[position].minAgeLimit.toString()
        holder.itemView.maxAge_txt.text = myList[position].maxAgeLimit.toString()
        holder.itemView.maxMembers_txt.text = myList[position].maxMembers.toString()
        holder.itemView.city_txt.text = myList[position].city
    }

    fun setData(newList: List<Room>){
        myList = newList
        notifyDataSetChanged()
    }
}