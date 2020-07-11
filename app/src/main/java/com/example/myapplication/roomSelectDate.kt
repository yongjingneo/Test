package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_room_select_date.*
import java.util.*

lateinit var dateRoom:String
class roomSelectDate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_select_date)
        val actionbar = supportActionBar
        actionbar!!.title="Room Reservation"

        val today = Calendar.getInstance().timeInMillis
        calendarRoom.minDate = today

        //textView71.text = today.toString()

        calendarRoom.setOnDateChangeListener(CalendarView.OnDateChangeListener { _, year, month, dayOfMonth ->
            dateRoom = dayOfMonth.toString() + "/" + (month + 1) + "/" + year
            //textView71.text = dateRoom
        })

        btnNext21.setOnClickListener {
            startActivity(Intent(this, roomSelectTime::class.java))
        }
    }
}
