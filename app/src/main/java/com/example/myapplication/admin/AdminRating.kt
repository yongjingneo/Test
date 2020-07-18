package com.example.myapplication.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.myapplication.*
import com.example.myapplication.R
import com.google.firebase.database.*

class AdminRating : AppCompatActivity() {

    lateinit var reviewList: MutableList<Review>
    lateinit var ref: DatabaseReference
    lateinit var listRatingData: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_rating)
        reviewList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("reviews")


        listRatingData = findViewById(R.id.listRatingData)

        val actionbar = supportActionBar
        actionbar!!.title="Ratings"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                if(p0!!.exists()){
                    for (h in p0.children){
                        val review = h.getValue(Review::class.java)
                        reviewList.add(review!!)
                    }

                    val adapter = ReviewAdapter(applicationContext,R.layout.rating,reviewList)
                    listRatingData.adapter = adapter
                }
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}