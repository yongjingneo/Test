package com.example.myapplication

import com.google.firebase.database.Exclude
import java.util.*

class tableReservation (
    @get:Exclude
    val id: String,
    val date: String ,
    val time: String,
    val tableNo: Int
){
    constructor():this("","","",0)
}