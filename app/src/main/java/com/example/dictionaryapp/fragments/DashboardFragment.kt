package com.example.dictionaryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.dictionaryapp.R
import com.example.dictionaryapp.utilities.MyFragmentManager

class DashboardFragment : Fragment() {
    private var rootView: View? = null
    private var clRoom: ConstraintLayout? = null
    private var clPeople: ConstraintLayout? = null

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_dashboard,container,false)
        init()
        setListeners()
        return rootView
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    /**
     * init ui components here.
     */
    private fun init() {
        clRoom = rootView!!.findViewById(R.id.cl_room)
        clPeople = rootView!!.findViewById(R.id.cl_people)
    }


    /**
     * setup Toolbar title here.
     */
    private fun setupToolbar() {
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar!!.title = getString(R.string.str_toolbar_title)
    }

    /**
     * set click listeners to view here.
     */
    private fun setListeners() {
        clRoom!!.setOnClickListener {
            MyFragmentManager.callFragment((activity as AppCompatActivity).supportFragmentManager,AvailableRoomListFragment(),R.id.container,null)
        }

        clPeople!!.setOnClickListener {
            MyFragmentManager.callFragment((activity as AppCompatActivity).supportFragmentManager,PeoplesListFragment(),R.id.container,null)
        }
    }
}