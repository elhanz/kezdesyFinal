package com.example.kezdesy.api

import com.example.kezdesy.model.*
import com.google.gson.JsonObject
import okhttp3.ResponseBody

import retrofit2.Call
import retrofit2.http.*
import com.google.android.gms.common.internal.safeparcel.SafeParcelable

interface ApiKezdesy {


    @GET("getUserByToken")
    fun getUserByToken(@Header("Authorization") token: String): Call<ResponseBody>

    @POST("register")
    fun createUser(@Body user: RegisterModel): Call<ResponseBody>

    @POST("login")
    fun login(@Query("email") email: String, @Query("password") password: String): Call<JsonObject>


    @POST("update")
    fun updateUser(@Header("Authorization") token: String, @Body user: UpdateUserModel): Call<ResponseBody>


    @POST("deleteUser")
    fun deleteUser(@Header("Authorization") token: String, @Body deleteModel: DeleteModel ): Call<ResponseBody>


    @POST("room/create")
    fun createRoom(@Header("Authorization") token:String, @Body room:  RoomCreateModel): Call<ResponseBody>

//    @GET("room/getAllRooms")
//    fun findRooms(@Body user: CreateUserAsGoogle): Call<ResponseBody>


}