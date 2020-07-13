package com.example.myapplication.roomReservation

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
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val today = Calendar.getInstance().timeInMillis
        calendarRoom.minDate = today

        dateRoom = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString() + "/" +
                    (Calendar.getInstance().get(Calendar.MONTH) + 1).toString() + "/" +
                    Calendar.getInstance().get(Calendar.YEAR).toString()


        calendarRoom.setOnDateChangeListener(CalendarView.OnDateChangeListener { _, year, month, dayOfMonth ->
            dateRoom = dayOfMonth.toString() + "/" + (month + 1) + "/" + year
        })

        btnNext21.setOnClickListener {
            startActivity(Intent(this, roomSelectTime::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
