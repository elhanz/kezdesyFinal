package com.example.kezdesy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.kezdesy.api.ApiConfig
import com.example.kezdesy.model.RoomCreateModel
import kotlinx.android.synthetic.main.activity_create_room.*
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_room)



        RegisterButton.setOnClickListener {


            val room = RoomCreateModel(
                txtEmail.text.toString(),
                txtCity.text.toString(),
                txtHeader.text.toString(),
                txtDescription.text.toString(),
                txtMinAge.text.toString().toInt(),
                txtMaxAge.text.toString().toInt(),
                txtMaxMembers.text.toString().toInt(),
                txtInterests.text.toString() //TODO
            )
            addRoom(room) {
                if (it?.source() != null) {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun addRoom(roomData: RoomCreateModel, onResult: (ResponseBody?) -> Unit) {
        val apiUser = ApiConfig.API_USER
        apiUser.createRoom(roomData).enqueue(
            object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val addedRoom = response.body()
                    onResult(addedRoom)
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun profilePage(view: View) {
        val intent = Intent(this, ProfilePageActivity::class.java)
        startActivity(intent)
        finish()
    }

}