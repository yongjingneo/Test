package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_room_select_date.*

class roomSelectDate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_select_date)

        btnNext2.setOnClickListener {
            startActivity(Intent(this, roomSelectTime::class.java))
        }
    }
}
