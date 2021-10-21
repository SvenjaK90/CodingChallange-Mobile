package com.example.codingchallenge_mobile

import android.os.Handler
import android.os.Looper
import com.example.codingchallenge_mobile.objects.GotCharacter
import com.example.codingchallenge_mobile.objects.GotHouse
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import org.json.JSONObject


class FetchGotData {

    private val executor: Executor = Executors.newSingleThreadExecutor()
    private val handler: Handler = Handler(Looper.getMainLooper())

    private var house = GotHouse()
    private var character = GotCharacter()
    private val houses: ArrayList<GotHouse> = arrayListOf()

    private fun parseHouse(jsonObject: JSONObject): GotHouse {
        val url = jsonObject.optString("url")
        val name = jsonObject.optString("name")
        val region = jsonObject.optString("region")
        val coatofarms = jsonObject.optString("coatOfArms")
        val words = jsonObject.optString("words")
        val titles = arrayListOf<String>()
        val jsonTitles = jsonObject.getJSONArray("titles")
        for (i in 0 until jsonTitles.length()) {
            titles.add(jsonTitles.optString(i))
        }
        val seats = arrayListOf<String>()
        val jsonSeats = jsonObject.getJSONArray("seats")
        for (i in 0 until jsonSeats.length()) {
            seats.add(jsonSeats.optString(i))
        }
        val currentLord = jsonObject.optString("currentLord")
        val heir = jsonObject.optString("heir")
        val overlord = jsonObject.optString("overlord")
        val founded = jsonObject.optString("founded")
        val founder = jsonObject.optString("founder")
        val diedOut = jsonObject.optString("diedOut")

        val ancestralWeapons = arrayListOf<String>()
        val jsonWaepons = jsonObject.getJSONArray("ancestralWeapons")
        for (i in 0 until jsonWaepons.length()) {
            ancestralWeapons.add(jsonWaepons.optString(i))
        }
        val cadetBranches = arrayListOf<String>()
        val jsonBranches = jsonObject.getJSONArray("cadetBranches")
        for (i in 0 until jsonBranches.length()) {
            cadetBranches.add(jsonBranches.optString(i))
        }

        val swornMembers = arrayListOf<String>()
        val jsonSworn = jsonObject.getJSONArray("swornMembers")
        for (i in 0 until jsonSworn.length()) {
            swornMembers.add(jsonSworn.optString(i))
        }

        return GotHouse(
            url,
            name,
            region,
            coatofarms,
            words,
            titles,
            seats,
            currentLord,
            heir,
            overlord,
            founded,
            founder,
            diedOut,
            ancestralWeapons,
            cadetBranches,
            swornMembers
        )
    }


    fun fetchOneCharacter(characterurl: URL, callback: (result: GotCharacter) -> Unit) {
        executor.execute {
            with(characterurl.openConnection() as HttpURLConnection) {
                if (responseCode == 200) {
                    inputStream.bufferedReader().use {
                        it.readLines().forEach { line ->
                            println(line)
                            val jsonObject = JSONObject(line)
                            val name = jsonObject.optString("name")
                            val culture = jsonObject.optString("culture")

                            character = GotCharacter(name, culture)
                        }

                    }
                }
            }

            handler.post {
                callback.invoke(character)
            }
        }

    }

    fun fetchAllHouses(callback: (result: ArrayList<GotHouse>) -> Unit) {
        val url = URL("https://anapioficeandfire.com/api/houses")
        executor.execute {
            with(url.openConnection() as HttpURLConnection) {
                if (responseCode == 200) {

                    inputStream.bufferedReader().use {

                        it.readLines().forEach { line ->
                            println(line)
                            val jsonArray = JSONArray(line)

                            for (i in 0 until jsonArray.length()) {
                                val jsonObject = jsonArray.getJSONObject(i)
                                houses.add(parseHouse(jsonObject))
                            }

                        }
                    }
                }
            }

            handler.post {
                callback.invoke(houses)
            }
        }

    }

    fun fetchOneHouse(houseurl: URL, callback: (result: GotHouse) -> Unit) {
        executor.execute {
            with(houseurl.openConnection() as HttpURLConnection) {
                if (responseCode == 200){
                    inputStream.bufferedReader().use {
                        it.readLines().forEach { line ->
                            println(line)
                            val jsonObject = JSONObject(line)
                            house = parseHouse(jsonObject)
                       }

                    }
                }

            }

            handler.post {
                callback.invoke(house)
            }
        }

    }
}
