package com.example.codingchallenge_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codingchallenge_mobile.adapter.HouseListAdapter
import com.example.codingchallenge_mobile.objects.GotHouse


class MainActivity : AppCompatActivity() {
    var houses: ArrayList<GotHouse> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        FetchGotData().fetchAllHouses { result ->
            houses = result
            val recyclerView: RecyclerView = findViewById(R.id.recyclerview_houses)
            val adapterResult = HouseListAdapter(this, houses)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapterResult
        }


    }
}
