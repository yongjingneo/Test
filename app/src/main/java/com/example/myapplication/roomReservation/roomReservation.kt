package com.example.myapplication.roomReservation

import com.google.firebase.database.Exclude

class roomReservation(
    @get:Exclude
    val id: String,
    val mail: String,
    val date: String,
    val time: String){

    constructor():this("","","",""){

    }
}