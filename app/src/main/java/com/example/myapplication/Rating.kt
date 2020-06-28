package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_rating.*

class Rating : AppCompatActivity() {

    //lateinit var handler: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        //handler = DatabaseHelper(this)

        val rate = findViewById<View>(R.id.ratingBar) as RatingBar
        val submit = findViewById<View>(R.id.btnRate) as Button

        submit.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"Your rating: "+rate.rating.toString(),Toast.LENGTH_LONG).show()
            /*handler.insertMealData(
                rate.rating.toString(),
                review.text.toString()
            )*/
            //Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show()
            val builder = AlertDialog.Builder(this)

            builder.setTitle("Exit")
            builder.setMessage("Are you sure you want to exit?")

            builder.setPositiveButton("Yes"){dialog, which ->
                finish()
            }

            builder.setNegativeButton("No"){dialog, which ->
                Toast.makeText(this,"You clicked over no button", Toast.LENGTH_LONG).show()
            }

            builder.setNeutralButton("Cancel"){dialog, which ->
                Toast.makeText(this,"You clicked over cancel button", Toast.LENGTH_LONG).show()
            }

            val dialog:AlertDialog = builder.create()
            dialog.show()
        })
    }
}
