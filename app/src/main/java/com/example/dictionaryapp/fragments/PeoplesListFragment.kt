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
import com.example.dictionaryapp.adapters.PeopleListAdapter
import com.example.dictionaryapp.model.People
import com.example.dictionaryapp.repository.Response
import com.example.dictionaryapp.utilities.BaseFragment

class PeoplesListFragment : BaseFragment() {
    private val TAG = PeoplesListFragment::class.java.simpleName
    private var rootView: View? = null
    private var tvWarning: TextView? = null
    private var rvPeoples: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_peoples_list,container,false)

        initUI()
        setupToolbar()
        fetchAllPeoplesData()
        observeData()
        return rootView
    }

    /**
     * init all ui components here.
     */
    private fun initUI() {
        rvPeoples = rootView!!.findViewById(R.id.rv_peoples)
        tvWarning = rootView!!.findViewById(R.id.tvWarning)
        //init adapter
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        rvPeoples!!.layoutManager = llm
    }

    /**
     * setup Toolbar title here.
     */
    private fun setupToolbar() {
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.title = getString(R.string.str_people)
    }

    /**
     * service call to get all the available people
     */
    private fun fetchAllPeoplesData() {
        (activity as MainActivity).mainViewModel.getPeoplesData()
    }

    private fun observeData() {
        (activity as MainActivity).mainViewModel.peoplesLiveData.observe((activity as MainActivity),{
            when (it) {
                is Response.Loading -> {
                    Log.i(TAG,"Response.Loading")
                    showProgressDialog(getString(R.string.str_fetch_people))
                }
                is Response.Success -> {
                    hideProgressDialog()
                    it.data?.let {
                        Log.i(TAG,"observeData: $it")
                        setAdapter(it)
                    }
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
    private fun setAdapter(factsList: List<People>) {
        if (factsList.isNotEmpty()) {
            val adapter = PeopleListAdapter(requireContext())
            adapter.submitList(factsList)
            rvPeoples!!.adapter = adapter
            rvPeoples!!.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
            rvPeoples!!.visibility = View.VISIBLE
            tvWarning!!.visibility = View.GONE
        } else {
            rvPeoples!!.visibility = View.GONE
            tvWarning!!.visibility = View.VISIBLE
        }
    }
}