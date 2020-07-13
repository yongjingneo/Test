package com.example.myapplication.tableReservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_reserve_table.*
import java.util.*

var date:String = ""

class tableSelectDate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve_table)

        val actionbar = supportActionBar
        actionbar!!.title="Table Reservation"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val today = Calendar.getInstance().timeInMillis
        calendarTable.minDate = today

        date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString() + "/" +
                (Calendar.getInstance().get(Calendar.MONTH) + 1).toString() + "/" +
                Calendar.getInstance().get(Calendar.YEAR).toString()


        calendarTable.setOnDateChangeListener(CalendarView.OnDateChangeListener { _, year, month, dayOfMonth ->
            date = dayOfMonth.toString() + "/" + (month + 1) + "/" + year
        })

        btnNext2.setOnClickListener {
            startActivity(Intent(this, tableSelectTime::class.java))
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
