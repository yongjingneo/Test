package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_confirm_room.*

class confirmRoom : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_room)
        val actionbar = supportActionBar
        actionbar!!.title="Room Reservation"

        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser!!.email.toString()

        txtRmail.text = user
        txtRdate.text = dateRoom
        txtRtime.text = timeRoom
    }
}
