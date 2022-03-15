package com.example.dictionaryapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.model.Room

class RoomListAdapter(val context: Context) :
    ListAdapter<Room,RoomListAdapter.ViewHolder>(DiffUtils()) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvId: TextView = itemView.findViewById(R.id.tv_id)
        var tvIsOccupied: TextView = itemView.findViewById(R.id.tv_occupied_status)
        var tvMaxOccupancy: TextView = itemView.findViewById(R.id.tv_max_occupancy)
        var tvCreatedAt: TextView = itemView.findViewById(R.id.tv_created_at)
        @SuppressLint("SetTextI18n") fun bind(item: Room) {
            tvId.text = "ID - ${item.id}"
            val status = if (item.isOccupied) "Yes" else "NO"
            tvIsOccupied.text = "Status - $status"
            tvMaxOccupancy.text = "Max - ${item.maxOccupancy}"
            tvCreatedAt.text = "Created AT - ${item.createdAt}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.room_cell_item,parent,false)
        val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        rootView!!.layoutParams = lp
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder,position: Int) {
        val Room = getItem(position)
        holder.bind(Room)
    }

    class DiffUtils : DiffUtil.ItemCallback<Room>() {
        override fun areItemsTheSame(oldItem: Room,newItem: Room): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Room,newItem: Room): Boolean {
            return oldItem == newItem
        }
    }
}