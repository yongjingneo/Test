package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.roomReservation.Reservation
import com.example.myapplication.tableReservation.checkTableReservation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val actionbar = supportActionBar
        actionbar!!.title="Profile"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()
        displayProfile()

        btnChgPassword.setOnClickListener {
            startActivity(Intent(this, ChangePassword::class.java))
        }

        btnCheckRoom.setOnClickListener {
            startActivity(Intent(this, Reservation::class.java))
        }

        btnCheckTable.setOnClickListener {
            startActivity(Intent(this, checkTableReservation::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun displayProfile(){
        val user = auth.currentUser
        if(user != null){

            txtEmail.setText(user.email)
            //updateProfile()

        }
    }

}
