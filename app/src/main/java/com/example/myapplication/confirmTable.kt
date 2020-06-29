package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Spinner
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_confirm_table.*
import kotlinx.android.synthetic.main.activity_reserve_table.*
import kotlinx.android.synthetic.main.activity_table_select_time.*

class confirmTable : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_table)

        //var dateCalendar = findViewById<CalendarView>(R.id.calendarTable)
        //var timeOption = findViewById<Spinner>(R.id.timeOption)
        //var tableOption = findViewById<Spinner>(R.id.tableOption)

        //txtTdate.setText(dateCalendar.date.toString())
        //txtTtime.text = timeOption.selectedItemPosition.toString()
        //txtTtable.text = tableOption.selectedItemPosition.toString()
    }
}
