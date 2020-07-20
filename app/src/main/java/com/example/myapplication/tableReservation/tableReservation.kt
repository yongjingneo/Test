package com.example.myapplication.tableReservation

import com.google.firebase.database.Exclude

class tableReservation(
    @get:Exclude
    val id: String,
    val mail: String,
    val nameT: String,
    val phoneNo:String,
    val date: String,
    val time: String,
    val bigTable: Int,
    val smallTable: Int
) {
    constructor():this("","","","","","",0,0){

    }
}