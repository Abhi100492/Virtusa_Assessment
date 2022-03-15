package com.example.dictionaryapp.api

import com.example.dictionaryapp.model.People
import com.example.dictionaryapp.model.Room
import retrofit2.Response
import retrofit2.http.GET

interface DictionaryService {
    @GET("rooms")
    suspend fun getRooms(): Response<List<Room>>

    @GET("people")
    suspend fun getPeople(): Response<List<People>>
}