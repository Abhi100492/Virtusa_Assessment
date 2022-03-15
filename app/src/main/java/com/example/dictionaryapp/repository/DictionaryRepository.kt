package com.example.dictionaryapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dictionaryapp.R
import com.example.dictionaryapp.model.People
import com.example.dictionaryapp.model.Room
import com.example.dictionaryapp.utilities.NetworkUtils
import com.example.dictionaryapp.api.DictionaryService

class DictionaryRepository(private val dictionaryService: DictionaryService,private val context: Context) {
    private val roomsLiveData = MutableLiveData<Response<List<Room>>>()
    val roomsData: LiveData<Response<List<Room>>> get() = roomsLiveData

    private val peoplesLiveData = MutableLiveData<Response<List<People>>>()
    val peoplesData: LiveData<Response<List<People>>> get() = peoplesLiveData

    suspend fun getAvailableRooms() {
        if (NetworkUtils.isInternetAvailable(context)) {
            try {
                roomsLiveData.postValue(Response.Loading())
                val result = dictionaryService.getRooms()
                if (result.body() != null) {
                    roomsLiveData.postValue(Response.Success(result.body()))
                } else {
                    roomsLiveData.postValue(Response.Error(context.getString(R.string.str_api_error)))
                }
            } catch (e: Exception) {
                roomsLiveData.postValue(Response.Error(e.message.toString()))
            }
        } else {
            roomsLiveData.postValue(Response.Error(context.getString(R.string.str_no_internet)))
        }
    }

    suspend fun getPeoples() {
        if (NetworkUtils.isInternetAvailable(context)) {
            try {
                peoplesLiveData.postValue(Response.Loading())
                val result = dictionaryService.getPeople()
                if (result.body() != null) {
                    peoplesLiveData.postValue(Response.Success(result.body()))
                } else {
                    peoplesLiveData.postValue(Response.Error(context.getString(R.string.str_api_error)))
                }
            } catch (e: Exception) {
                peoplesLiveData.postValue(Response.Error(e.message.toString()))
            }
        } else {
            peoplesLiveData.postValue(Response.Error(context.getString(R.string.str_no_internet)))
        }
    }
}