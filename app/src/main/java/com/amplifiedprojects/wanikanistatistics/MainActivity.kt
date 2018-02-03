package com.amplifiedprojects.wanikanistatistics

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.logging.Level

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val retrofit = Retrofit.Builder().baseUrl("https://www.wanikani.com/api/v2/").addConverterFactory(MoshiConverterFactory.create()).build()

        val service = retrofit.create(WaniKaniService::class.java)
        service.levelProgressions().enqueue(object : Callback<WaniKaniService.LevelProgressions> {
            override fun onResponse(call: Call<WaniKaniService.LevelProgressions>, response: Response<WaniKaniService.LevelProgressions>) {
                // Deal with the response here
                val levelProgressionDatas = response.body()!!.data!!.map { it.data!! }

                adapter = RecyclerAdapter(levelProgressionDatas)
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<WaniKaniService.LevelProgressions>, t: Throwable) {
                // Deal with the error here
            }
        })
    }
}
