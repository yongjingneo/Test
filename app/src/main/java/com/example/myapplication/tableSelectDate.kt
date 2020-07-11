package com.example.myapplication.Table

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_reserve_table.*
import java.util.*

lateinit var date:String

class tableSelectDate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve_table)
        val actionbar = supportActionBar
        actionbar!!.title="Table Reservation"

        val today = Calendar.getInstance().timeInMillis
        calendarTable.minDate = today

        //textView7.text = today.toString()

        calendarTable.setOnDateChangeListener(CalendarView.OnDateChangeListener { _, year, month, dayOfMonth ->
            date = dayOfMonth.toString() + "/" + (month + 1) + "/" + year
            //textView7.text = date
        })

        btnNext2.setOnClickListener {
            startActivity(Intent(this, tableSelectTime::class.java))
        }

    }

}
