package com.example.dictionaryapp.application

import android.app.Application
import com.example.dictionaryapp.api.DictionaryService
import com.example.dictionaryapp.api.RetrofitHelper
import com.example.dictionaryapp.repository.DictionaryRepository

class App : Application() {
    lateinit var repository: DictionaryRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val service = RetrofitHelper.getInstance().create(DictionaryService::class.java)
        repository = DictionaryRepository(service, applicationContext)
    }
}