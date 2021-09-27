package com.example.gdirectionspoc.ui.editLocation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdirectionspoc.R
import com.example.gdirectionspoc.databinding.LayoutWaypointItemBinding
import com.example.gdirectionspoc.ui.editLocation.interfaces.WayPointItemClickListener

class WayPointItemAdapter(private val wayPoints: ArrayList<String>, private val wayPointItemClickListener: WayPointItemClickListener): RecyclerView.Adapter<WayPointItemAdapter.WayPointViewHolder>() {

    inner class WayPointViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val wayPointTv: TextView = itemView.findViewById(R.id.way_point_item_tv)
        val wayPointDeleteIv: ImageView = itemView.findViewById(R.id.delete_way_point_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WayPointViewHolder =
        WayPointViewHolder(LayoutWaypointItemBinding.inflate(LayoutInflater.from(parent.context)).root)

    override fun onBindViewHolder(holder: WayPointViewHolder, position: Int) {
        if (position >= wayPoints.size){
            holder.wayPointDeleteIv.visibility = View.GONE
            holder.wayPointTv.text = holder.itemView.context.getString(R.string.enter_way_points_hint_text)
            holder.wayPointTv.setOnClickListener {
                wayPointItemClickListener.onAddItemClick()
            }
        } else {
            holder.wayPointTv.text = wayPoints[position]

            holder.wayPointDeleteIv.visibility = View.VISIBLE
            holder.wayPointDeleteIv.setOnClickListener {
                wayPointItemClickListener.onClickDelete(position)
            }
        }

    }

    override fun getItemCount(): Int = wayPoints.size+1
}