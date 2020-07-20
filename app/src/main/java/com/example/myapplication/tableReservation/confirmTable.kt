package com.example.myapplication.tableReservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.*
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_confirm_table.*



class confirmTable : AppCompatActivity() {

    lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_table)

        val actionbar = supportActionBar
        actionbar!!.title="Table Reservation"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser!!.email.toString()

        txtTmail.text = user
        txtTdate.text = date
        txtTtime.text = time
        txtTBigtable.text = bigTable.toString()
        txtTSmalltable.text = smallTable.toString()

        btnConfirmTable.setOnClickListener {
            if(editTextTName.text.isEmpty()){
                editTextTName.error = "Name is required."
                return@setOnClickListener
            }
            if(editTextTPhoneNo.text.isEmpty()){
                editTextTPhoneNo.error = "Phone number is required."
                return@setOnClickListener
            }
            saveTableReservation()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun saveTableReservation(){

        val ref = FirebaseDatabase.getInstance().getReference("tableReservations")
        val tableId = ref.push().key

        val table = tableReservation(tableId!!,txtTmail.text.toString(), editTextTName.text.toString(),editTextTPhoneNo.text.toString(),
            date,time, bigTable, smallTable)

        ref.child(tableId).setValue(table).addOnCompleteListener {
            val confirmDialog = AlertDialog.Builder(this)
            confirmDialog.setTitle("Reservation success")
            confirmDialog.setMessage("Your reservation is successfully made. Do you want to continue to order meal?")
            confirmDialog.setPositiveButton("Yes",{ _, _ ->
                startActivity(Intent(this, MealOrdering::class.java))
                finish()
            })
            confirmDialog.setNegativeButton("No",{_,_->
                startActivity(Intent(this, MainPage::class.java))
                finish()
            })
            confirmDialog.show()
        }

    }
}
