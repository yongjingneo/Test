package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.myItem1 -> startActivity(Intent(this,Profile::class.java))
                R.id.myItem2 -> startActivity(Intent(this,ViewMenu::class.java))
                R.id.myItem3 -> startActivity(Intent(this, roomSelectDate::class.java))
                R.id.myItem4 -> startActivity(Intent(this, tableSelectDate::class.java))
                R.id.myItem5 -> startActivity(Intent(this,Payment::class.java))
                R.id.myItem6 -> startActivity(Intent(this@MainPage,Rating::class.java))
            }
            true
        }
        val actionbar = supportActionBar
        actionbar!!.title="Home Page"

        auth = FirebaseAuth.getInstance()

        btnLogOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, Login::class.java))
            finish()
        }

//        btnProfile.setOnClickListener {
//            startActivity(Intent(this,Profile::class.java))
//            //finish()
//        }

        btnReserveTable.setOnClickListener {
            startActivity(Intent(this, tableSelectDate::class.java))
        }

        btnReserveRoom.setOnClickListener {
            startActivity(Intent(this, roomSelectDate::class.java))
        }

        btnMealOrdering.setOnClickListener{
            startActivity(Intent(this,ViewMenu::class.java))
        }

//        btnRating.setOnClickListener{
//            startActivity(Intent(this@MainPage,Rating::class.java))
//
//        }

//        btnPayment.setOnClickListener{
//            startActivity(Intent(this,Payment::class.java))
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
