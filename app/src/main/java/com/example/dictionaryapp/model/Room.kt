package com.example.dictionaryapp.model

data class Room(
    val createdAt: String,
    val id: String,
    val isOccupied: Boolean,
    val maxOccupancy: Int
)