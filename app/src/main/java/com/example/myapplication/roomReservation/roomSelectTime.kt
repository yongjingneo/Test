package com.example.myapplication.roomReservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_room_select_time.*

lateinit var timeRoom:String

class roomSelectTime : AppCompatActivity() {

    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_select_time)

        val actionbar = supportActionBar
        actionbar!!.title="Room Reservation"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        ref = FirebaseDatabase.getInstance().getReference("roomReservations")

        val time1: String = "10:00am-12:00pm"
        val time2: String = "12:30pm-2:30pm"
        val time3: String = "3:00pm-5:00pm"
        val time4: String = "5:30pm-7:30pm"
        val time5: String = "8:00pm-10:00pm"

        var timeOptions = arrayOf("Select time",time1, time2, time3, time4, time5)

        timeOptionRoom.adapter = ArrayAdapter<String>(this,
            R.layout.support_simple_spinner_dropdown_item,timeOptions)

        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    for(t in p0.children){
                        if(t.getValue(roomReservation::class.java)?.date == dateRoom &&
                            t.getValue(roomReservation::class.java)?.time == time1){
                            timeOptions.set(1,"")
                        }
                        if(t.getValue(roomReservation::class.java)?.date == dateRoom &&
                            t.getValue(roomReservation::class.java)?.time == time2){
                            timeOptions.set(2,"")
                        }
                        if(t.getValue(roomReservation::class.java)?.date == dateRoom &&
                            t.getValue(roomReservation::class.java)?.time == time3){
                            timeOptions.set(3,"")
                        }
                        if(t.getValue(roomReservation::class.java)?.date == dateRoom &&
                            t.getValue(roomReservation::class.java)?.time == time4){
                            timeOptions.set(4,"")
                        }
                        if(t.getValue(roomReservation::class.java)?.date == dateRoom &&
                            t.getValue(roomReservation::class.java)?.time == time5){
                            timeOptions.set(5,"")
                        }
                    }
                }
            }

        } )

        btnNext31.setOnClickListener {
            if(timeOptionRoom.selectedItemPosition == 0){
                val messageBox = AlertDialog.Builder(this)
                messageBox.setTitle("Message")
                messageBox.setMessage("Please select time.")
                messageBox.setPositiveButton("OK",{ _, _ ->
                })
                messageBox.show()
            }else{
                recordTime()
                startActivity(Intent(this, confirmRoom::class.java))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun recordTime(){
        if(timeOptionRoom.selectedItemPosition == 1){
            timeRoom = "10:00am-12:00pm"
        }
        if(timeOptionRoom.selectedItemPosition == 2){
            timeRoom = "12:30pm-2:30pm"
        }
        if(timeOptionRoom.selectedItemPosition == 3){
            timeRoom = "3:00pm-5:00pm"
        }
        if(timeOptionRoom.selectedItemPosition == 4){
            timeRoom = "5:30pm-7:30pm"
        }
        if(timeOptionRoom.selectedItemPosition == 5){
            timeRoom = "8:00pm-10:00pm"
        }
    }
}