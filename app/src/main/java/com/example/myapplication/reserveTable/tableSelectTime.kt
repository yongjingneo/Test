package com.example.myapplication.reserveTable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_table_select_time.*

class tableSelectTime : AppCompatActivity() {

//    lateinit var option1: Spinner
//    lateinit var option2: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_select_time)

//        option1 = findViewById(R.id.timeOption) as Spinner
//        option2 = findViewById(R.id.tableOption)

        var timeOptions = arrayOf("10:00am-12:00pm", "12:30pm-2:30pm", "3:00pm-5:00pm", "5:30pm-7:30pm", "8:00pm-10:00pm")
        var tableOptions = arrayOf("1","2","3","4","5","6","7","8")

        timeOption.adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,timeOptions)
        tableOption.adapter = ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, tableOptions)

        btnReserveTable.setOnClickListener {
            startActivity(Intent(this, confirmTable::class.java))
        }
    }
}
