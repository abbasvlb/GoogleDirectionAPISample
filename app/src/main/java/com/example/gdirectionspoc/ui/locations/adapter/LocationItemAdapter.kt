package com.example.gdirectionspoc.ui.locations.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdirectionspoc.R
import com.example.gdirectionspoc.databinding.LayoutLocationItemBinding
import com.example.gdirectionspoc.pojo.GDirectionResponse
import com.example.gdirectionspoc.ui.locations.interfaces.LocationItemClickListener

class LocationItemAdapter(private val locations: ArrayList<GDirectionResponse>, private val locationItemClickListener: LocationItemClickListener) : RecyclerView.Adapter<LocationItemAdapter.LocationItemViewHolder>() {

    inner class LocationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val startLocationTv: TextView = itemView.findViewById(R.id.start_location_item_tv)
        val wayPointLocationTv: TextView = itemView.findViewById(R.id.way_point_location_item_tv)
        val getLocationIv: ImageView = itemView.findViewById(R.id.get_location_iv)
        val editLocationsIv: ImageView = itemView.findViewById(R.id.edit_location_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationItemViewHolder =
        LocationItemViewHolder(LayoutLocationItemBinding.inflate(LayoutInflater.from(parent.context)).root)

    override fun onBindViewHolder(holder: LocationItemViewHolder, position: Int) {
        holder.startLocationTv.text = locations[position].routes[0].legs[0].startAddress

        val stringBuilder = StringBuilder()
        for (leg in locations[position].routes[0].legs){
            stringBuilder.append(leg.end_address).append(" ")
        }

        holder.wayPointLocationTv.text = stringBuilder.toString()

        holder.editLocationsIv.setOnClickListener {
            locationItemClickListener.onEditClicked(locations[position].id)
        }

        holder.getLocationIv.setOnClickListener {
            locationItemClickListener.onGetLocationClicked(locations[position].id)
        }

        holder.itemView.setOnClickListener {
            locationItemClickListener.onItemClicked(locations[position].id)

        }
    }

    override fun getItemCount(): Int = locations.size

}