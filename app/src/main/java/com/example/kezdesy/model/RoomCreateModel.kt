package com.example.kezdesy.model

data class RoomCreateModel (
    val email: String,
    val city: String,
    val header: String,
    val description: String,
    val minAge: Int,
    val maxAge: Int,
    val maxMembers: Int,
    val interests : Set<Interests>
)