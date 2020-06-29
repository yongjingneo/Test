package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_reserve_table.*

class tableSelectDate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve_table)

        btnNext.setOnClickListener {
            startActivity(Intent(this, tableSelectTime::class.java))
            //finish()
        }

    }
}
