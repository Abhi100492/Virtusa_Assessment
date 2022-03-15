package com.example.dictionaryapp

import android.app.ProgressDialog
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dictionaryapp.application.App
import com.example.dictionaryapp.fragments.DashboardFragment
import com.example.dictionaryapp.utilities.MyFragmentManager
import com.example.dictionaryapp.viewmodel.MainViewModel
import com.example.dictionaryapp.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        initializeComponents()
        loadDefaultFragment()
    }

    private fun initUI() {
        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setCancelable(false)
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
    }

    /**
     * initialize your MVVM components here.
     */
    private fun initializeComponents() {
        val repository = (application as App).repository
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)
    }


    /**
     * load Default dashboard fragment here.
     */
    private fun loadDefaultFragment() {
        MyFragmentManager.callFragment(supportFragmentManager,DashboardFragment(),R.id.container,null)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun showProgressDialog(title: String) {
        if (!this@MainActivity.isFinishing && !progressDialog.isShowing) {
            progressDialog.setTitle(getString(R.string.str_please_wait))
            progressDialog.setMessage(title)
            progressDialog.show()
        }
    }

    fun hideProgressDialog(){
        if (!this@MainActivity.isFinishing && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }
}