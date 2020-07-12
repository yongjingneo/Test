package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.roomReservation.roomAdapter
import com.example.myapplication.roomReservation.roomReservation
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_reservation.*

class Reservation : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var roomList:MutableList<roomReservation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        val actionbar = supportActionBar
        actionbar!!.title="Reservation"

        ref = FirebaseDatabase.getInstance().getReference("roomReservations")
        roomList = mutableListOf()

        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    roomList.clear()
                    for(r in p0.children){
                        val room = r.getValue(roomReservation::class.java)
                        roomList.add(room!!)
                    }

                    val adapter = roomAdapter(applicationContext, R.layout.rooms, roomList)
                    listRoom.adapter = adapter
                }
            }

        } )
    }
}
