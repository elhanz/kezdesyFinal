package com.example.kezdesy.model

data class Room(
    val id :Long,
    val city: String,
    val header: String,
    val description: String,
    val minAge: Int,
    val maxAge: Int,
    val maxMembers: String,
    val interests : Set<Interests>
)
