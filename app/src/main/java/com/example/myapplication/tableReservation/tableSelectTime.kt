package com.example.myapplication.tableReservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.myapplication.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_check_table_reservation.*
import kotlinx.android.synthetic.main.activity_table_select_time.*
import java.lang.reflect.Array
import java.util.*
import kotlin.collections.ArrayList

lateinit var time:String

class tableSelectTime : AppCompatActivity() {

    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_select_time)
        val actionbar = supportActionBar
        actionbar!!.title="Table Reservation"

        ref = FirebaseDatabase.getInstance().getReference("tableReservations")

        val time1: String = "10:00am-12:00pm"
        val time2: String = "12:30pm-2:30pm"
        val time3: String = "3:00pm-5:00pm"
        val time4: String = "5:30pm-7:30pm"
        val time5: String = "8:00pm-10:00pm"


        var timeOptions = arrayOf("Select time",time1, time2, time3, time4, time5)

        timeOption.adapter = ArrayAdapter<String>(this,
            R.layout.support_simple_spinner_dropdown_item,timeOptions)


        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    for(t in p0.children){
                        if(t.getValue(tableReservation::class.java)?.date == date &&
                            t.getValue(tableReservation::class.java)?.time == time1 &&
                            t.getValue(tableReservation::class.java)?.size == size &&
                            t.getValue(tableReservation::class.java)?.no == no    ){
                            timeOptions.set(1,"")
                        }
                        if(t.getValue(tableReservation::class.java)?.date == date &&
                            t.getValue(tableReservation::class.java)?.time == time2 &&
                            t.getValue(tableReservation::class.java)?.size == size &&
                            t.getValue(tableReservation::class.java)?.no == no    ){
                            timeOptions.set(2,"")
                        }
                        if(t.getValue(tableReservation::class.java)?.date == date &&
                            t.getValue(tableReservation::class.java)?.time == time3 &&
                            t.getValue(tableReservation::class.java)?.size == size &&
                            t.getValue(tableReservation::class.java)?.no == no    ){
                            timeOptions.set(3,"")
                        }
                        if(t.getValue(tableReservation::class.java)?.date == date &&
                            t.getValue(tableReservation::class.java)?.time == time4 &&
                            t.getValue(tableReservation::class.java)?.size == size &&
                            t.getValue(tableReservation::class.java)?.no == no    ){
                            timeOptions.set(4,"")
                        }
                        if(t.getValue(tableReservation::class.java)?.date == date &&
                            t.getValue(tableReservation::class.java)?.time == time5 &&
                            t.getValue(tableReservation::class.java)?.size == size &&
                            t.getValue(tableReservation::class.java)?.no == no    ){
                            timeOptions.set(5,"")
                        }
                    }
                }
            }

        } )

        btnNext3.setOnClickListener {
            recordTime()
            startActivity(Intent(this, confirmTable::class.java))
        }
    }

    private fun recordTime(){
        if(timeOption.selectedItemPosition == 1){
            time = "10:00am-12:00pm"
        }
        if(timeOption.selectedItemPosition == 2){
            time = "12:30pm-2:30pm"
        }
        if(timeOption.selectedItemPosition == 3){
            time = "3:00pm-5:00pm"
        }
        if(timeOption.selectedItemPosition == 4){
            time = "5:30pm-7:30pm"
        }
        if(timeOption.selectedItemPosition == 5){
            time = "8:00pm-10:00pm"
        }
    }
}
