package com.example.kezdesy.model

data class User(
    val id :Long,
    val first_name: String,
    val last_name: String,
    val age: Int,
    val city: String,
    val email: String,
    val password: String,
    val profile_pic: String,
    val gender: String,
    val roles : Collection<Role>

)