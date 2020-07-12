package com.example.myapplication.tableReservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_confirm_table.*



class confirmTable : AppCompatActivity() {

    lateinit var auth:FirebaseAuth
    //lateinit var tableList: MutableList<tableReservation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_table)
        val actionbar = supportActionBar
        actionbar!!.title="Table Reservation"

        auth = FirebaseAuth.getInstance()
        //tableList = mutableListOf()
        //ref= FirebaseDatabase.getInstance().getReference("tableReservation")

        val user = auth.currentUser!!.email.toString()

        txtTmail.text = user
        txtTdate.text = date
        txtTtime.text = time
        txtTtable.text = size
        txtTNo.text = no

        btnConfirmTable.setOnClickListener {
            saveTableReservation()
        }
    }

    private fun saveTableReservation(){

        val ref = FirebaseDatabase.getInstance().getReference("tableReservations")
        val tableId = ref.push().key

        val table = tableReservation(tableId!!,txtTmail.text.toString(),date,time,size, no)

        ref.child(tableId).setValue(table).addOnCompleteListener {
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
