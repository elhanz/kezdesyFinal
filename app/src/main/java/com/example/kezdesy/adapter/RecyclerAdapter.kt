package app.com.retrofitwithrecyclerviewkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kezdesy.R
import com.example.kezdesy.model.Room

class RecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    var myList: List<Room> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.header.text = myList.get(position).header
        holder.description.text = "Description:  " + myList.get(position).description
        holder.minAge.text = "Age range: " + myList.get(position).minAgeLimit.toString() + "-" + myList.get(position).maxAgeLimit.toString()
        holder.maxMembers.text = "Max members: " + myList.get(position).maxMembers.toString()
        holder.city.text = "City: " + myList.get(position).city

    }

    fun setMovieListItems(roomList: List<Room>) {
        this.myList = roomList;
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val header: TextView = itemView!!.findViewById(R.id.header_txt)
        val description: TextView = itemView!!.findViewById(R.id.description_txt)
        val minAge: TextView = itemView!!.findViewById(R.id.age_txt)
        val maxMembers: TextView = itemView!!.findViewById(R.id.maxMembers_txt)
        val city: TextView = itemView!!.findViewById(R.id.city_txt)

    }
}
