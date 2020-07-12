package com.example.myapplication.tableReservation

import com.google.firebase.database.Exclude

class tableReservation(
    @get:Exclude
    val id: String,
    val mail: String,
    val date: String,
    val time: String,
    val size: String,
    val no: String
) {
    constructor():this("","","","","",""){

    }
}