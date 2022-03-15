package com.example.dictionaryapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.MainActivity
import com.example.dictionaryapp.R
import com.example.dictionaryapp.adapters.RoomListAdapter
import com.example.dictionaryapp.model.Room
import com.example.dictionaryapp.repository.Response
import com.example.dictionaryapp.utilities.BaseFragment

class AvailableRoomListFragment : BaseFragment() {
    private val TAG = AvailableRoomListFragment::class.java.simpleName
    private var rootView: View? = null
    private var tvWarning: TextView? = null
    private var rvRooms: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_available_room_list,container,false)
        initUI()
        setupToolbar()
        fetchAllAvailableRoomsData()
        observeData()
        return rootView
    }

    /**
     * init all ui components here.
     */
    private fun initUI() {
        rvRooms = rootView!!.findViewById(R.id.rv_rooms)
        tvWarning = rootView!!.findViewById(R.id.tvWarning)
        //init adapter
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        rvRooms!!.layoutManager = llm
    }

    /**
     * setup Toolbar title here.
     */
    private fun setupToolbar() {
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.title = getString(R.string.str_rooms)
    }

    /**
     * service call to get all the available people
     */
    private fun fetchAllAvailableRoomsData() {
        (activity as MainActivity).mainViewModel.getAvailableRoomsData()
    }

    private fun observeData() {
        (activity as MainActivity).mainViewModel.roomLiveData.observe((activity as MainActivity),{
            when (it) {
                is Response.Loading -> {
                    showProgressDialog(getString(R.string.str_fetch_room_data))
                }
                is Response.Success -> {
                    hideProgressDialog()
                    it.data?.let {
                        Log.i(TAG,"observeData: $it")
                        setAdapter(it)                    }
                }
                is Response.Error -> {
                    hideProgressDialog()
                    val errorMsg = it.errorMsg ?: getString(R.string.str_api_error)
                    Toast.makeText(context,errorMsg,Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    /**
     * set accounts adapter here.
     */
    private fun setAdapter(roomsList: List<Room>) {
        if (roomsList.isNotEmpty()) {
            val adapter = RoomListAdapter(requireContext())
            adapter.submitList(roomsList)
            rvRooms!!.adapter = adapter
            rvRooms!!.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
            rvRooms!!.visibility = View.VISIBLE
            tvWarning!!.visibility = View.GONE
        } else {
            rvRooms!!.visibility = View.GONE
            tvWarning!!.visibility = View.VISIBLE
        }
    }
}