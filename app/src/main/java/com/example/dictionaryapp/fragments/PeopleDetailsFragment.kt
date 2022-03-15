package com.example.dictionaryapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dictionaryapp.R
import com.example.dictionaryapp.model.People
import com.example.dictionaryapp.utilities.Constants
import com.squareup.picasso.Picasso

class PeopleDetailsFragment : Fragment() {
    private var rootView: View? = null
    private var ivProfilePic: ImageView? = null
    private var tvName: TextView? = null
    private var tvJobTitle: TextView? = null
    private var tvEmail: TextView? = null
    private var tvFavColor: TextView? = null
    private var tvCreatedAt: TextView? = null
    private var btnBack: Button? = null
    private lateinit var people: People
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            people = arguments?.getSerializable(Constants.KEY_DATA) as People
        }
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_people_details,container,false)
        initUI()
        setListeners()
        setupToolbar()
        setData()
        return rootView
    }

    private fun initUI() {
        ivProfilePic = rootView!!.findViewById(R.id.iv_profile_pic)
        tvName = rootView!!.findViewById(R.id.tv_name)
        tvJobTitle = rootView!!.findViewById(R.id.tv_job_title)
        tvEmail = rootView!!.findViewById(R.id.tv_email)
        tvFavColor = rootView!!.findViewById(R.id.tv_fav_color)
        tvCreatedAt = rootView!!.findViewById(R.id.tv_created_at)
        btnBack = rootView!!.findViewById(R.id.btn_go_back)
    }

    private fun setListeners() {
        btnBack!!.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    /**
     * setup Toolbar title here.
     */
    private fun setupToolbar() {
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.title = getString(R.string.str_details)
    }

    @SuppressLint("SetTextI18n") fun setData() {
        tvName!!.text = "Name - ${people.firstName}  ${people.lastName}"
        tvJobTitle!!.text = "Job Title - ${people.jobtitle}"
        tvEmail!!.text = "Email - ${people.email}"
        tvFavColor!!.text = "Fav Colour - ${people.favouriteColor}"
        tvCreatedAt!!.text = "Created Time - ${people.createdAt}"

        try {
            Picasso.get()!!
                .load(people.avatar)
                .placeholder(android.R.drawable.stat_notify_error)
                .noFade()
                .into(ivProfilePic)
        } catch (e: Exception) {
            Log.i("FactsAdapter","onBindViewHolder: " + e.message.toString())
        }
    }
}