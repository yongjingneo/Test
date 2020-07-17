package com.example.myapplication.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.tableReservation.tableAdapter
import com.example.myapplication.tableReservation.tableReservation
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_admin_table.*

class AdminTable : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var tableList:MutableList<tableReservation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_table)

        val actionbar = supportActionBar
        actionbar!!.title="Table Reservation"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

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
                            val table = t.getValue(tableReservation::class.java)
                            tableList.add(table!!)
                    }

                    val adapter = tableAdapter(applicationContext,
                        R.layout.tables, tableList)
                    listAdminTable.adapter = adapter
                }
            }

        } )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
