package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class Rating : AppCompatActivity() {

    lateinit var editTextName:EditText
    lateinit var ratingBar:RatingBar
    lateinit var btnSave:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        /*val rate = findViewById<View>(R.id.ratingBar) as RatingBar
        val submit = findViewById<View>(R.id.btnRate) as Button

        submit.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"Your rating: "+rate.rating.toString(),Toast.LENGTH_LONG).show()

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
        })*/

        editTextName = findViewById(R.id.editTextName)
        ratingBar = findViewById(R.id.ratingBar)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener{
            saveRating()
        }
    }

    private fun saveRating(){
        val name = editTextName.text.toString().trim()

        if(name.isEmpty()){
            editTextName.error="Please enter a review"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("reviews")
        val heroId = ref.push().key

        val review = Review(heroId,name, ratingBar.numStars)

        ref.child(heroId.toString()).setValue(review).addOnCompleteListener{
            Toast.makeText(applicationContext, "Reviews saved successfully.", Toast.LENGTH_LONG).show()
        }

    }
}
