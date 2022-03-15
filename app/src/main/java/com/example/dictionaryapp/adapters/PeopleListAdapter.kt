package com.example.dictionaryapp.adapters

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.fragments.PeopleDetailsFragment
import com.example.dictionaryapp.model.People
import com.example.dictionaryapp.utilities.Constants
import com.example.dictionaryapp.utilities.MyFragmentManager
import com.squareup.picasso.Picasso

class PeopleListAdapter(val context: Context) :
    ListAdapter<People,PeopleListAdapter.ViewHolder>(DiffUtils()) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvEmail: TextView = itemView.findViewById(R.id.tv_email)
        var ivProfileImg: ImageView = itemView.findViewById(R.id.iv_logo)
        var cell: ConstraintLayout = itemView.findViewById(R.id.row)
        fun bind(item: People) {
            tvTitle.text = item.firstName
            tvEmail.text = item.email
            try {
                Picasso.get()!!
                    .load(item.avatar)
                    .placeholder(android.R.drawable.stat_notify_error)
                    .noFade()
                    .into(ivProfileImg)
            } catch (e: Exception) {
                Log.i("FactsAdapter","onBindViewHolder: " + e.message.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.people_cell_item,parent,false)
        val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        rootView!!.layoutParams = lp
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder,position: Int) {
        val people = getItem(position)
        holder.bind(people)
        holder.cell.setOnClickListener {
            Toast.makeText(context,people.firstName,Toast.LENGTH_SHORT).show()
            val bundle = Bundle()
            bundle.putSerializable(Constants.KEY_DATA, people)
            MyFragmentManager.callFragment(
                (context as AppCompatActivity).supportFragmentManager,
                PeopleDetailsFragment(),
                R.id.container,
                bundle
            )
        }
    }

    class DiffUtils : DiffUtil.ItemCallback<People>() {
        override fun areItemsTheSame(oldItem: People,newItem: People): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: People,newItem: People): Boolean {
            return oldItem == newItem
        }
    }
}