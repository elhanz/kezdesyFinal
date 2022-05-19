package com.example.kezdesy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.kezdesy.api.ApiConfig
import com.example.kezdesy.model.RegisterModel
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        RegisterButton.setOnClickListener {

            val user = RegisterModel(
                textInputFirstName.text.toString(),
                textInputLastName.text.toString(),
                textInputAge.text.toString().toInt(),
                textInputCity.text.toString(),
                textInputEmail.text.toString(),
                textInputPassword.text.toString(),
                textInputGender.text.toString()
            )
            addUser(user) {
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


    fun addUser(userData: RegisterModel, onResult: (ResponseBody?) -> Unit) {
        val apiUser = ApiConfig.API_USER
        apiUser.createUser(userData).enqueue(
            object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun onLoginClick(view: View?) {
        startActivity(Intent(this, LoginActivity::class.java))
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
    }

}


