package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.no
import com.example.myapplication.size
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
            //saveTableReservation()
        }
    }

//    private fun saveTableReservation(){
//        val text1 = txtTmail.text.toString()
//        val text2 = txtTdate.text.toString()
//        val text3 = txtTtime.text.toString()
//        val text4 = txtTtable.text.toString()
//        val text5 = txtTNo.text.toString()
//
//        if(text1.isEmpty()){
//            Toast.makeText(applicationContext,"Unsuccessful.", Toast.LENGTH_SHORT).show()
//        }
//
//        val ref = FirebaseDatabase.getInstance().getReference("tableReservations")
//        val tableId = ref.push().key
//
//        val table = tableReservation(tableId!!,text1,text2,text3,text4,text5)
//
//        ref.child(tableId).setValue(table).addOnCompleteListener {
//            Toast.makeText(applicationContext,"Reserve Successful.", Toast.LENGTH_SHORT).show()
//        }
//
//        //Toast.makeText(applicationContext,"ful.", Toast.LENGTH_SHORT).show()
//    }
}
