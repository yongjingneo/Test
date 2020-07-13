package com.example.myapplication.roomReservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_reservation.*

class Reservation : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var roomList:MutableList<roomReservation>
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val actionbar = supportActionBar
        actionbar!!.title="Reservation"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


        auth = FirebaseAuth.getInstance()
        val mail = auth.currentUser?.email

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
                        if(r.getValue(roomReservation::class.java)?.mail == mail){
                            val room = r.getValue(roomReservation::class.java)
                            roomList.add(room!!)
                        }
                    }

                    val adapter = roomAdapter(applicationContext,
                        R.layout.rooms, roomList)
                    listRoom.adapter = adapter
                }
            }

        } )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
