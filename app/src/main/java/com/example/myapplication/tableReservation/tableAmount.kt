package com.example.myapplication.tableReservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_table_amount.*

var bigTable: Int = 0
var smallTable: Int = 0

class tableAmount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_amount)

        val actionbar = supportActionBar
        actionbar!!.title="Table Reservation"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        textView42.setText("0")
        textView43.setText("0")

        editTextPeople.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(editTextPeople.text.isEmpty()){
                    textView42.setText("0")
                    textView43.setText("0")
                    return
                }
                val number = editTextPeople.text.toString().trim().toInt()
                if(number < 1 || number > 42){
                    textView42.setText("0")
                    textView43.setText("0")
                    editTextPeople.error = "Number must between 1 to 42."
                }else{
                    calTable(number)
                }
            }

        })

        button.setOnClickListener {
            bigTable = textView42.text.toString().toInt()
            smallTable = textView43.text.toString().toInt()
            startActivity(Intent(this, tableSelectDate::class.java))
        }
    }

    fun calTable(number: Int){
        if(number <= 4){
            textView42.setText("0")
            textView43.setText("1")
        }else if(number > 4 && number <= 10){
            textView42.setText("1")
            textView43.setText("0")
        }else if(number > 10 && number <= 14){
            textView42.setText("1")
            textView43.setText("1")
        }else if(number > 14 && number <= 20){
            textView42.setText("2")
            textView43.setText("0")
        }else if(number > 20 && number <= 24){
            textView42.setText("2")
            textView43.setText("1")
        }else if(number > 24 && number <= 30){
            textView42.setText("3")
            textView43.setText("0")
        }else if(number > 30 && number <= 34){
            textView42.setText("3")
            textView43.setText("1")
        }else if(number > 34 && number <= 38){
            textView42.setText("3")
            textView43.setText("2")
        }else if(number > 38 && number <= 42){
            textView42.setText("3")
            textView43.setText("3")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
