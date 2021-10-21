package com.example.codingchallenge_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import java.net.URL

class HouseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house)

        //Get Extra
        val houseurl = intent.getStringExtra("houseUrl").toString()

        //Setup UI-Elements
        val houseTitle: TextView = findViewById(R.id.textViewHouseTitle)
        val houseRegion: TextView = findViewById(R.id.textViewHouseRegion)
        val houseLord: TextView = findViewById(R.id.textViewLord)
        val houseCoat: TextView = findViewById(R.id.textViewCoatOfArms)
        val houseWords: TextView = findViewById(R.id.textViewWords)
        val houseHeir: TextView = findViewById(R.id.textViewHeir)
        val houseOverlord: TextView = findViewById(R.id.textViewOverlord)
        val houseFounded: TextView = findViewById(R.id.textViewFounded)
        val houseFounder: TextView = findViewById(R.id.textViewFounder)
        val houseDied: TextView = findViewById(R.id.textViewDied)
        val houseTitlesLabel: TextView = findViewById(R.id.textViewTitlesLabel)
        val houseSeatsLabel: TextView = findViewById(R.id.textViewSeatsLabel)
        val houseWeaponsLabel: TextView = findViewById(R.id.textViewWeaponsLabel)
        val houselistViewTitles: ListView = findViewById(R.id.listViewTitles)
        val houselistViewSeats: ListView = findViewById(R.id.listViewSeats)
        val houselistViewWeapons: ListView = findViewById(R.id.listViewWeapons)


        //Fetch data of got-house by url (given by extras)
        FetchGotData().fetchOneHouse(URL(houseurl)) { gotHouse ->

            //Set content of Textviews
            houseTitle.text = gotHouse.name
            houseRegion.text = getString(R.string.house_detail_region, checkContent(gotHouse.region))
            houseCoat.text = getString(R.string.house_detail_coat, checkContent(gotHouse.coatOfArms))
            houseWords.text = getString(R.string.house_detail_words, checkContent(gotHouse.words))
            houseFounded.text = getString(R.string.house_detail_founded, checkContent(gotHouse.founded))
            houseDied.text = getString(R.string.house_detail_died, checkContent(gotHouse.diedOut))

            //Fetch and set data
            getCharacterInfo(gotHouse.heir, houseHeir, R.string.house_detail_heir)
            getCharacterInfo(gotHouse.founder, houseLord, R.string.house_detail_founder)
            getCharacterInfo(gotHouse.currentLord, houseFounder, R.string.house_detail_currentlord)


            if(gotHouse.overlord.isNotEmpty()){
                FetchGotData().fetchOneHouse(URL(gotHouse.overlord)) { house ->
                    houseOverlord.text = getString(R.string.house_detail_overlord, house.name)

                    houseOverlord.setOnClickListener {
                        val intent = Intent(this, HouseActivity::class.java)
                        intent.putExtra("houseUrl", house.url)
                        this.startActivity(intent)
                        finish()
                    }
                }
            } else {
                houseOverlord.text = getString(R.string.house_detail_overlord, "No Information")
            }

            //Setup Lists
            setListVisibilty(gotHouse.titles,houseTitlesLabel, houselistViewTitles)
            setListVisibilty(gotHouse.seats,houseSeatsLabel, houselistViewSeats)
            setListVisibilty(gotHouse.ancestralWeapons,houseWeaponsLabel, houselistViewWeapons)
        }


    }

    /***If No Content available display Default-Text***/
    private fun checkContent(content:String):String{
        return if (content.isEmpty() ) "No Information"
        else content
    }

    /***Check if Character is available and set text***/
    private fun getCharacterInfo(characterUrl: String, textView: TextView, stringRes: Int){
        if(characterUrl.isNotEmpty()){
            FetchGotData().fetchOneCharacter(URL(characterUrl)) { character ->
                textView.text = getString(stringRes, character.name)
            }
        } else {
            textView.text = getString(stringRes, checkContent(characterUrl))
        }
    }

    /***Check if List is empty and set Visisbilty***/
    private fun setListVisibilty(gotlist: ArrayList<String>, label: TextView, list: ListView){
        if(gotlist[0].isEmpty()){
            list.visibility = View.GONE
            label.visibility = View.GONE
        }
        val adapterGot = ArrayAdapter(this,
            R.layout.listview_item_title, gotlist)
        list.adapter = adapterGot
    }
}