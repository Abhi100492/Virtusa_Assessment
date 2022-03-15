package com.example.dictionaryapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dictionaryapp.repository.DictionaryRepository

class MainViewModelFactory(private val dictionaryRepository: DictionaryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(dictionaryRepository) as T
    }
}