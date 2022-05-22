package com.example.kezdesy

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.com.retrofitwithrecyclerviewkotlin.RecyclerAdapter
import com.example.kezdesy.api.ApiConfig
import com.example.kezdesy.model.Room
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerAdapter = RecyclerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter


        val apiUser = ApiConfig.API_USER
        apiUser.getRooms().enqueue(
            object : Callback<List<Room>> {

                override fun onResponse(call: Call<List<Room>>, response: Response<List<Room>>) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@MainActivity,
                            "There are our rooms",
                            Toast.LENGTH_LONG
                        ).show()
                        if (response?.body() != null)
                            recyclerAdapter.setMovieListItems(response.body()!!)

                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Rooms Failed 1 ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<Room>>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "Rooms Failed 2",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        )
    }
}


//Initialize the bottom navigation view
//create bottom navigation view object
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)
//        val navController = findNavController(R.id.nav_fragment)
//        bottomNavigationView.setupWithNavController(navController)






