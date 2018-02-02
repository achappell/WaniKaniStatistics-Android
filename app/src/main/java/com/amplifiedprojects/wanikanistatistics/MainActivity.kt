package com.amplifiedprojects.wanikanistatistics

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.logging.Level

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val retrofit = Retrofit.Builder().baseUrl("https://www.wanikani.com/api/v2/").addConverterFactory(MoshiConverterFactory.create()).build()

        val service = retrofit.create(WaniKaniService::class.java)
        service.levelProgressions().enqueue(object : Callback<WaniKaniService.LevelProgression> {
            override fun onResponse(call: Call<WaniKaniService.LevelProgression>, response: Response<WaniKaniService.LevelProgression>) {
                // Deal with the response here
                val dummyObject = response.body()
            }

            override fun onFailure(call: Call<WaniKaniService.LevelProgression>, t: Throwable) {
                // Deal with the error here
            }
        })
    }
}
