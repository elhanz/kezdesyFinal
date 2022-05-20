package com.example.kezdesy.model

data class RoomCreateModel(
    val email: String,
    val city: String,
    val header: String,
    val description: String,
    val minAgeLimit: Int,
    val maxAgeLimit: Int,
    val maxMembers: Int

)