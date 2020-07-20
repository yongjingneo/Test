package com.example.myapplication.roomReservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.MainPage
import com.example.myapplication.MealOrdering
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_confirm_room.*
import kotlinx.android.synthetic.main.activity_table_select_time.*

class confirmRoom : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_room)

        val actionbar = supportActionBar
        actionbar!!.title="Room Reservation"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser!!.email.toString()

        txtRmail.text = user
        txtRdate.text = dateRoom
        txtRtime.text = timeRoom

        btnConfirmRoom.setOnClickListener {
            if(editTextRName.text.isEmpty()){
                editTextRName.error = "Name is required."
                return@setOnClickListener
            }
            if(editTextRPN.text.isEmpty()){
                editTextRPN.error = "Phone number is required."
                return@setOnClickListener
            }
            saveRoomReservation()

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun saveRoomReservation(){
        val ref = FirebaseDatabase.getInstance().getReference("roomReservations")
        val roomId = ref.push().key

        val room = roomReservation(roomId!!,txtRmail.text.toString(), editTextRName.text.toString(),editTextRPN.text.toString(),
            dateRoom, timeRoom)

        ref.child(roomId).setValue(room).addOnCompleteListener {
            val confirmDialog = AlertDialog.Builder(this)
            confirmDialog.setTitle("Reservation success")
            confirmDialog.setMessage("Your reservation is successfully made.")
            confirmDialog.setPositiveButton("OK",{_,_->
                startActivity(Intent(this, MainPage::class.java))
                finish()
            })
            confirmDialog.show()
        }
    }
}
