package com.example.myapplication

import android.provider.ContactsContract

class tableReservation(
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