package com.example.codingchallenge_mobile.objects

class GotHouse{
    var url: String = ""
    var name: String = ""
    var region: String = ""
    var coatOfArms: String = ""
    var words: String = ""
    var titles: ArrayList<String> = arrayListOf()
    var seats: ArrayList<String> = arrayListOf()
    var currentLord: String = ""
    var heir: String = ""
    var overlord: String = ""
    var founded: String = ""
    var founder: String = ""
    var diedOut: String = ""
    var ancestralWeapons: ArrayList<String> = arrayListOf()
    var cadetBranches: ArrayList<String> = arrayListOf()
    var swornMembers: ArrayList<String> = arrayListOf()
    
    constructor(url: String,
    name: String,
    region: String,
    coatOfArms: String,
    words: String,
    titles: ArrayList<String>,
    seats: ArrayList<String>,
    currentLord: String,
    heir: String,
    overlord: String,
    founded: String,
    founder: String,
    diedOut: String,
    ancestralWeapons: ArrayList<String>,
    cadetBranches: ArrayList<String>,
    swornMembers: ArrayList<String>
){
        this.url = url
        this.name = name
        this.region = region
        this.coatOfArms = coatOfArms
        this.words = words
        this.titles = titles
        this.seats = seats
        this.currentLord = currentLord
        this.heir = heir
        this.overlord = overlord
        this.founded = founded
        this.founder = founder
        this.diedOut = diedOut
        this.ancestralWeapons = ancestralWeapons
        this.cadetBranches = cadetBranches
        this.swornMembers = swornMembers
    }

constructor()
}