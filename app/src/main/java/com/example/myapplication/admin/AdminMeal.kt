package com.example.myapplication.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.myapplication.Order
import com.example.myapplication.OrderAdapter
import com.example.myapplication.R
import com.google.firebase.database.*

class AdminMeal : AppCompatActivity() {

    lateinit var orderList: MutableList<Order>
    lateinit var ref: DatabaseReference
    lateinit var listViewData: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_meal)

        orderList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("orders")


        listViewData = findViewById(R.id.listMealData)

        val actionbar = supportActionBar
        actionbar!!.title="Meal Orders"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                if(p0!!.exists()){
                    for (h in p0.children){
                        val order = h.getValue(Order::class.java)
                            orderList.add(order!!)
                    }

                    val adapter = OrderAdapter(applicationContext,R.layout.orders,orderList)
                    listViewData.adapter = adapter
                }
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

