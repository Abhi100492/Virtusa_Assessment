package com.example.dictionaryapp.model

import java.io.Serializable

data class People(
    val avatar: String,
    val createdAt: String,
    val email: String,
    val favouriteColor: String,
    val firstName: String,
    val id: String,
    val jobtitle: String,
    val lastName: String
) : Serializable