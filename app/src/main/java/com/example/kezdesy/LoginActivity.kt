package com.example.kezdesy

import android.content.Intent
import android.os.Build
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import com.example.kezdesy.api.ApiConfig

import android.content.Context
import android.widget.Toast

import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.admanager.AdManagerAdView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    lateinit var mAdManagerAdView : AdManagerAdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAdManagerAdView = findViewById(R.id.adManagerAdVew)
        val adRequest = AdManagerAdRequest.Builder().build()
        mAdManagerAdView.loadAd(adRequest)



        LoginButton.setOnClickListener {
            login(textInputEmail.text.toString().trim(), textInputPassword.text.toString().trim())
        }


    }

    fun onLoginClick(View: View?) {
        startActivity(Intent(this, RegisterActivity::class.java))
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
    }

    fun login(email: String, password: String) {
        val apiUser = ApiConfig.API_USER
        apiUser.login(email, password).enqueue(
            object : Callback<JsonObject> {
                override fun onResponse(
                    call: Call<JsonObject>,
                    response: Response<JsonObject>
                ) {
                    if (response.isSuccessful) {
                        val token = response.body()?.get("access_token")?.asString
                        saveToken(token!!)
                    } else {
                        Toast.makeText(this@LoginActivity, "Error", Toast.LENGTH_LONG).show()
                    }


                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "on failure", Toast.LENGTH_LONG).show()
                }
            }
        )

    }




    private fun saveToken(token: String) {
        val apiUser = ApiConfig.API_USER
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        var newToken = "Bearer " + token
        apiUser.getUserByToken(newToken).enqueue(
            object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val user = JSONObject(response.body()?.string())
                        edit.apply {
                            putString("TOKEN_KEY", newToken)
                            putString("FIRST_NAME", user.get("first_name").toString())
                            putString("LAST_NAME", user.get("last_name").toString())
                            putInt("AGE", user.get("age").toString().toInt())
                            putString("CITY", user.get("city").toString())
                            putString("EMAIL", user.get("email").toString())
                            putString("PROFILEPIC", user.get("profilePic").toString())
                            putString("GENDER", user.get("gender").toString())

                        }.apply()
                        val intent = Intent(this@LoginActivity, ProfilePageActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Can't go to the page ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Something go wrong..", Toast.LENGTH_LONG)
                        .show()
                }
            }
        )
    }



}