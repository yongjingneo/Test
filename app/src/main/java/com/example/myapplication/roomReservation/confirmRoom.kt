package com.example.myapplication.roomReservation

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.MainPage
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_confirm_room.*

class confirmRoom : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_room)
        val actionbar = supportActionBar
        actionbar!!.title="Room Reservation"

        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser!!.email.toString()

        txtRmail.text = user
        txtRdate.text = dateRoom
        txtRtime.text = timeRoom

        btnConfirmRoom.setOnClickListener {
            saveRoomReservation()
        }
    }

    private fun saveRoomReservation(){
        val ref = FirebaseDatabase.getInstance().getReference("roomReservations")
        val roomId = ref.push().key

        val room = roomReservation(roomId!!,txtRmail.text.toString(), dateRoom, timeRoom)

        ref.child(roomId).setValue(room).addOnCompleteListener {
            val confirmDialog = AlertDialog.Builder(this)
            confirmDialog.setTitle("Reservation success")
            confirmDialog.setMessage("Your reservation is successfully made.")
            confirmDialog.setPositiveButton("OK",{ _, _ ->
                startActivity(Intent(this, MainPage::class.java))
                finish()
            })
            confirmDialog.show()
        }
    }
}
