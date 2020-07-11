package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_table_select_time.*

lateinit var time:String

class tableSelectTime : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_select_time)
        val actionbar = supportActionBar
        actionbar!!.title="Table Reservation"

        var timeOptions = arrayOf("10:00am-12:00pm", "12:30pm-2:30pm", "3:00pm-5:00pm", "5:30pm-7:30pm", "8:00pm-10:00pm")

        timeOption.adapter = ArrayAdapter<String>(this,
            R.layout.support_simple_spinner_dropdown_item,timeOptions)

        btnNext3.setOnClickListener {
            recordTime()
            startActivity(Intent(this, confirmTable::class.java))
        }
    }

    private fun recordTime(){
        if(timeOption.selectedItemPosition == 0){
            time = "10:00am-12:00pm"
        }
        if(timeOption.selectedItemPosition == 1){
            time = "12:30pm-2:30pm"
        }
        if(timeOption.selectedItemPosition == 2){
            time = "3:00pm-5:00pm"
        }
        if(timeOption.selectedItemPosition == 3){
            time = "5:30pm-7:30pm"
        }
        if(timeOption.selectedItemPosition == 4){
            time = "8:00pm-10:00pm"
        }
    }
}
