package com.example.myapplication.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.roomReservation.roomAdapter
import com.example.myapplication.roomReservation.roomReservation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_admin_room.*

class AdminRoom : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var roomList:MutableList<roomReservation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_room)

        val actionbar = supportActionBar
        actionbar!!.title="Room Reservation"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

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

                    val adapter = roomAdapter(applicationContext,
                        R.layout.rooms, roomList)
                    listAdminRoom.adapter = adapter
                }
            }

        } )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
