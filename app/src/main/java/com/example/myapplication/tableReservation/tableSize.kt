package com.example.myapplication.tableReservation

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_table_size.*

var size: String = ""
var no: String = ""

class tableSize : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_size)
        val actionbar = supportActionBar
        actionbar!!.title="Table Reservation"

        radioTable1.text = ""
        radioTable2.text = ""
        radioTable3.text = ""

        spinnerTable.setSelection(0)

        var options = arrayOf("Please select table size", "Big Table", "Small Table")
        spinnerTable.adapter = ArrayAdapter<String>(this,
            R.layout.support_simple_spinner_dropdown_item, options)

        spinnerTable.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(spinnerTable.selectedItemPosition == 0){
                    radioTable1.text = ""
                    radioTable2.text = ""
                    radioTable3.text = ""
                    radioTable1.isEnabled = false
                    radioTable2.isEnabled = false
                    radioTable3.isEnabled = false
                }
                if(spinnerTable.selectedItemPosition == 1){
                    radioTable1.text = "Big Table #1"
                    radioTable2.text = "Big table #2"
                    radioTable1.isEnabled = true
                    radioTable2.isEnabled = true
                    radioTable3.isEnabled = false
                }
                if(spinnerTable.selectedItemPosition == 2){
                    radioTable1.text = "Small Table #1"
                    radioTable2.text = "Small table #2"
                    radioTable3.text = "Small table #3"
                    radioTable1.isEnabled = true
                    radioTable2.isEnabled = true
                    radioTable3.isEnabled = true
                }
            }

        }

        radioTable1.setOnClickListener{
            no = "1"
        }
        radioTable2.setOnClickListener{
            no = "2"
        }
        radioTable3.setOnClickListener{
            no = "3"
        }

        btnNext.setOnClickListener {
            if(spinnerTable.selectedItemPosition == 0){
                val messageBox = AlertDialog.Builder(this)
                messageBox.setTitle("Message")
                messageBox.setMessage("Please select table size.")
                messageBox.setPositiveButton("OK",{ _, _ ->
                })
                messageBox.show()
            }else{
                recordTableSize()
                startActivity(Intent(this, tableSelectDate::class.java))
            }
        }
    }

    fun recordTableSize(){
        if(spinnerTable.selectedItemPosition == 1){
            size = "Big"
        }
        if(spinnerTable.selectedItemPosition == 2){
            size = "Small"
        }
    }
}
