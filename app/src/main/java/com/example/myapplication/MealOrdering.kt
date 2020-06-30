package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlinx.android.synthetic.main.activity_meal_ordering.*

class MealOrdering : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_ordering)

        val actionbar = supportActionBar
        actionbar!!.title="Meal Ordering"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


        btnMakePayment.setOnClickListener{
            calculateTotal()
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
            }
         }

        private fun calculateTotal(){
            var total:Int = 0
            var price:Double = 0.00

            if(checkBox.isChecked){
                total = total + q1.text.toString().toInt()
                price = price + rate1.text.toString().toDouble()*q1.text.toString().toDouble()
            }

            if(checkBox2.isChecked){
                total = total + q2.text.toString().toInt()
                price = price + rate2.text.toString().toDouble()*q2.text.toString().toDouble()
            }

            if(checkBox3.isChecked){
                total = total + q3.text.toString().toInt()
                price = price + rate3.text.toString().toDouble()*q3.text.toString().toDouble()
            }

            if(checkBox4.isChecked){
                total = total + q4.text.toString().toInt()
                price = price + rate4.text.toString().toDouble()*q4.text.toString().toDouble()
            }

            if(checkBox5.isChecked){
                total = total + q5.text.toString().toInt()
                price = price + rate5.text.toString().toDouble()*q5.text.toString().toDouble()
            }

            if(checkBox6.isChecked){
                total = total + q6.text.toString().toInt()
                price = price + rate6.text.toString().toDouble()*q6.text.toString().toDouble()
            }

            totalItem.setText("Total item ordered\t\t\t" + total.toString())
            totalPrice.setText("Total Price\t\t\t" + String.format("%.2f", price).toDouble())

        }

}


