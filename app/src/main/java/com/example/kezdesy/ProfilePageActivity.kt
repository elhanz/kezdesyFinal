package com.example.kezdesy

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.kezdesy.api.ApiConfig
import com.example.kezdesy.model.DeleteModel
import com.example.kezdesy.model.UpdateUserModel
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)
        loadData()



        UpdateButton.setOnClickListener {
            val email = tvEmail.text.toString().trim()
            val first_name = tVFirstName.text.toString()
            val last_name = tvLastName.text.toString()
            val age = tvAge.text.toString().toInt()
            val gender = tvGender.text.toString()
            val city = tvCity.text.toString()


            val user = UpdateUserModel(email, first_name, last_name, age, gender, city)
            updateUser(user)
        }

        LogoutButton.setOnClickListener {
            logOut()
        }
        DeleteButton.setOnClickListener {
            val email = tvEmail.text.toString().trim()
            val deleteModel = DeleteModel(email)

            deleteUser(deleteModel)
        }

    }


    private fun updateUser(user: UpdateUserModel) {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        val apiUser = ApiConfig.API_USER
        apiUser.updateUser(sharedPreferences.getString("TOKEN_KEY", null)!!, user).enqueue(
            object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@ProfilePageActivity,
                            "Update is successful",
                            Toast.LENGTH_LONG
                        ).show()

                        edit.apply {
                            putString("EMAIL", user.email)
                            putString("FIRST_NAME", user.first_name)
                            putString("lAST_NAME", user.last_name)
                            putInt("AGE", user.age)
                            putString("CITY", user.city)
                            putString("GENDER", user.gender)
                        }.apply()
                    } else {
                        Toast.makeText(
                            this@ProfilePageActivity,
                            "Update failed",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(
                        this@ProfilePageActivity,
                        "Update failed2",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        )
    }

    private fun loadData() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)


        tvEmail.text = sharedPreferences.getString("EMAIL", null)
        tVFirstName.setText(sharedPreferences.getString("FIRST_NAME", null))
        tvLastName.setText(sharedPreferences.getString("LAST_NAME", null))
        tvAge.setText(sharedPreferences.getInt("AGE", 0).toString())
        tvGender.setText(sharedPreferences.getString("GENDER", null))
        tvCity.setText(sharedPreferences.getString("CITY", null))


    }

    fun logOut() {

        val sharedPreferences: SharedPreferences =
            getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.apply {
            putString("TOKEN_KEY", null)
            putString("FIRST_NAME", null)
            putString("LAST_NAME", null)
            putInt("AGE", 0)
            putString("CITY", null)
            putString("EMAIL", null)
            putString("PROFILEPIC", null)
            putString("GENDER", null)
        }.apply()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun deleteUser(deleteModel: DeleteModel) {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        val apiUser = ApiConfig.API_USER

        apiUser.deleteUser(sharedPreferences.getString("TOKEN_KEY", null)!!, deleteModel).enqueue(
            object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@ProfilePageActivity,
                            "Delete is successful",
                            Toast.LENGTH_LONG
                        ).show()

                        edit.apply {
                            putString("TOKEN_KEY", null)
                            putString("FIRST_NAME", null)
                            putString("LAST_NAME", null)
                            putInt("AGE", 0)
                            putString("CITY", null)
                            putString("EMAIL", null)
                            putString("PROFILEPIC", null)
                            putString("GENDER", null)
                        }.apply()

                    } else {
                        Toast.makeText(
                            this@ProfilePageActivity,
                            "Delete failed",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(
                        this@ProfilePageActivity,
                        "Delete failed2",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        )
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


    fun mainPage(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun CreateRoom(view: View) {
        val intent = Intent(this, CreateRoomActivity::class.java)
        startActivity(intent)
        finish()
    }

}