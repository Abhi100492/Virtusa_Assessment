package com.example.dictionaryapp.utilities

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dictionaryapp.R

abstract class BaseFragment : Fragment() {
    private var rootView: View? = null
    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_baseragment,container,false)
        return rootView
    }

    private fun init() {
        progressDialog = ProgressDialog(activity)
        progressDialog.setTitle(getString(R.string.str_please_wait))
        progressDialog.setCancelable(false)
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.isIndeterminate = false
    }

    fun showProgressDialog(title: String) {
        Log.i("TAG","showProgressDialog: $title")
        progressDialog.setMessage(title)
        if (!activity?.isFinishing!! && !progressDialog.isShowing)
            progressDialog.show()
    }

    fun hideProgressDialog() {
        Log.i("TAG","hideProgressDialog: ")
        if (!activity?.isFinishing!! && progressDialog.isShowing)
            progressDialog.dismiss()
    }
}