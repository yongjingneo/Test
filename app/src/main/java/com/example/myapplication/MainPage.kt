package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        auth = FirebaseAuth.getInstance()

        btnLogOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, Login::class.java))
            finish()
        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this,Profile::class.java))
            //finish()
        }

        btnReserveTable.setOnClickListener {
            startActivity(Intent(this, tableSelectDate::class.java))
        }

        btnReserveRoom.setOnClickListener {
            startActivity(Intent(this, roomSelectDate::class.java))
        }

        btnMealOrdering.setOnClickListener{
            startActivity(Intent(this,MealOrdering::class.java))
            finish()
        }

        btnRating.setOnClickListener{
            startActivity(Intent(this,Rating::class.java))
            finish()
        }

    }
}
