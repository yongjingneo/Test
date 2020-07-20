package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_user_meal_order.*

class UserMealOrder : AppCompatActivity() {

    lateinit var orderList: MutableList<Order>
    lateinit var ref: DatabaseReference
    lateinit var listViewData: ListView
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_meal_order)
        auth = FirebaseAuth.getInstance()
        val email = auth.currentUser?.email

        orderList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("orders")


        listViewData = findViewById(R.id.listUserMealData)

        val actionbar = supportActionBar
        actionbar!!.title="User Meal Orders"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                if(p0!!.exists()){
                    for (h in p0.children)
                    {if(h.getValue(Order::class.java)?.email == email)
                    {val order = h.getValue(Order::class.java)
                        orderList.add(order!!)}
                    }

                    val adapter = OrderAdapter(applicationContext,R.layout.orders,orderList)
                    listUserMealData.adapter = adapter
                }
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}



