package com.example.codingchallenge_mobile.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.codingchallenge_mobile.HouseActivity
import com.example.codingchallenge_mobile.R
import com.example.codingchallenge_mobile.objects.GotHouse
import kotlin.collections.ArrayList

class HouseListAdapter(
    applicationContext: Context,
    houseList:ArrayList<GotHouse>
) :
    RecyclerView.Adapter<HouseListAdapter.ViewHolder>() {

    private val context = applicationContext
    private val houses: ArrayList<GotHouse> = houseList

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val houseName:TextView = view.findViewById(R.id.textViewHouseName)
    }


    override fun getItemId(position: Int): Long {
        return 0
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_house_listitem, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.houseName.text = houses[position].name

        holder.itemView.setOnClickListener {
            showNext(houses[position])
        }

    }

    override fun getItemCount() = houses.size


    private fun showNext(house: GotHouse) {

        val intent = Intent(context, HouseActivity::class.java)
        intent.putExtra("houseUrl", house.url)

        context.startActivity(intent)
    }


}