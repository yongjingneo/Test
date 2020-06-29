package com.example.myapplication.reserveRoom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_room_select_time.*

class roomSelectTime : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_select_time)

        btnReserveRoom.setOnClickListener {
            startActivity(Intent(this, roomConfirm::class.java))
        }
    }
}
