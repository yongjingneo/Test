package com.example.myapplication.tableReservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_check_table_reservation.*

class checkTableReservation : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var tableList:MutableList<tableReservation>
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_table_reservation)

        val actionbar = supportActionBar
        actionbar!!.title="Reservation"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()
        val mail = auth.currentUser?.email

        ref = FirebaseDatabase.getInstance().getReference("tableReservations")
        tableList = mutableListOf()

        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    tableList.clear()
                    for(t in p0.children){
                        if(t.getValue(tableReservation::class.java)?.mail == mail){
                            val table = t.getValue(tableReservation::class.java)
                            tableList.add(table!!)
                        }
                    }

                    val adapter = tableAdapter(applicationContext,
                        R.layout.tables, tableList)
                    listTable.adapter = adapter
                }
            }

        } )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
