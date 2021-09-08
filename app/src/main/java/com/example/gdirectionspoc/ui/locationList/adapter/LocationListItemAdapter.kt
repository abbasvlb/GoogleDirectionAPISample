package com.example.gdirectionspoc.ui.locationList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdirectionspoc.R
import com.example.gdirectionspoc.databinding.LayoutLocationListItemBinding
import com.example.gdirectionspoc.pojo.GDirectionResponse
import com.example.gdirectionspoc.ui.locationList.interfaces.LocationsListItemClickListener

class LocationListItemAdapter(
    private val locations: ArrayList<GDirectionResponse?>,
    private val locationsListItemClickListener: LocationsListItemClickListener
) :
    RecyclerView.Adapter<LocationListItemAdapter.LocationListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationListItemViewHolder {
        val binding = LayoutLocationListItemBinding.inflate(
            LayoutInflater.from(parent.context)
        )

        return LocationListItemViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: LocationListItemViewHolder, position: Int) {
        holder.startLocationItemTv.text = locations[0]!!.routes[0].legs[0].startAddress

        val stringBuilder = StringBuilder()
        for (step in locations[0]!!.routes[0].legs[0].steps) {
            stringBuilder.append(step.endAddress)
        }

        holder.wayPointLocationItemTv.text = stringBuilder.toString()

        holder.editLocationItemIv.setOnClickListener {
            locationsListItemClickListener.onClickEdit(locations[position]!!.id)

        }

        holder.getLocationItemIv.setOnClickListener {
            locationsListItemClickListener.onClickGetLocation(locations[position]!!.id)

        }

        holder.itemView.setOnClickListener {
            locationsListItemClickListener.onClickItem(locations[position]!!.id)
        }

    }

    override fun getItemCount(): Int {
        return locations.size
    }

    inner class LocationListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val startLocationItemTv: TextView = itemView.findViewById(R.id.start_location_item_tv)
        val wayPointLocationItemTv: TextView =
            itemView.findViewById(R.id.way_point_location_item_tv)
        val editLocationItemIv: ImageView = itemView.findViewById(R.id.edit_location_iv)
        val getLocationItemIv: ImageView = itemView.findViewById(R.id.get_location_iv)


    }
}