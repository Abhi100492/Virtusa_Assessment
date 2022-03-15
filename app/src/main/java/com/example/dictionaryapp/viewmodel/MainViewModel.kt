package com.example.dictionaryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.model.People
import com.example.dictionaryapp.model.Room
import com.example.dictionaryapp.repository.DictionaryRepository
import com.example.dictionaryapp.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val dictionaryRepository: DictionaryRepository) : ViewModel() {
    val roomLiveData: LiveData<Response<List<Room>>>
        get() = dictionaryRepository.roomsData

    val peoplesLiveData: LiveData<Response<List<People>>>
        get() = dictionaryRepository.peoplesData

    fun getAvailableRoomsData() {
        viewModelScope.launch(Dispatchers.IO) {
            dictionaryRepository.getAvailableRooms()
        }
    }

    fun getPeoplesData() {
        viewModelScope.launch(Dispatchers.IO) {
            dictionaryRepository.getPeoples()
        }
    }
}