package com.example.myapplication.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Login
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_admin_main_page.*

class adminMainPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main_page)

        val actionbar = supportActionBar
        actionbar!!.title="Admin Page"

        btnAdminRoom.setOnClickListener {
            startActivity(Intent(this, AdminRoom::class.java))
        }

        btnAdminTable.setOnClickListener {
            startActivity(Intent(this, AdminTable::class.java))
        }

        btnAdminRating.setOnClickListener{
            startActivity(Intent(this,AdminRating::class.java))
        }
        btnAdminMeal.setOnClickListener {
            startActivity(Intent(this, AdminMeal::class.java))
        }

        btnAdminLogout.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }
}
