package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_room_select_time.*
import kotlinx.android.synthetic.main.activity_table_select_time.*

class roomSelectTime : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_select_time)

        var timeOptions2 = arrayOf("10:00am-12:00pm", "12:30pm-2:30pm", "3:00pm-5:00pm", "5:30pm-7:30pm", "8:00pm-10:00pm")

        timeOption2.adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,timeOptions2)

        btnReserveRoom.setOnClickListener {
            startActivity(Intent(this, roomConfirm::class.java))
        }
    }
}
