package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.com.example.myapplication.Payment
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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

            if(checkBox7.isChecked){
                total = total + q7.text.toString().toInt()
                price = price + rate7.text.toString().toDouble()*q7.text.toString().toDouble()
            }

            if(checkBox8.isChecked){
                total = total + q8.text.toString().toInt()
                price = price + rate8.text.toString().toDouble()*q8.text.toString().toDouble()
            }

            if(checkBox9.isChecked){
                total = total + q9.text.toString().toInt()
                price = price + rate9.text.toString().toDouble()*q9.text.toString().toDouble()
            }

            totalItem.setText("Total item ordered\t\t\t" + total.toString())
            totalPrice.setText("Total Price\t\t\t" + String.format("%.2f", price).toDouble())


            val totalOrderItem =total.toString()
            //val totalOrderPrice =price.toString()
            val totalOrderPrice =String.format("%.2f", price)
            val name = custName.text.toString().trim()

            if(name.isEmpty()){
                custName.error = "Please enter a name"
                return
            }

            val ref = FirebaseDatabase.getInstance().getReference("orders")
            val orderId = ref.push().key

            if(total==0){
                //Toast.makeText(applicationContext,"Error.",Toast.LENGTH_LONG).show()
                val builder = AlertDialog.Builder(this)

                builder.setTitle("Exit")
                builder.setMessage("You have not ordered any meal. Are you sure you want to exit?")

                builder.setPositiveButton("Yes"){dialog, which ->
                    startActivity(Intent(this,MainPage::class.java))
                }

                builder.setNegativeButton("No"){dialog, which ->
                    //startActivity(Intent(this,MealOrdering::class.java))
                    Toast.makeText(this,"You may select your meal now.", Toast.LENGTH_LONG).show()
                }

                val dialog:AlertDialog = builder.create()
                dialog.show()
            }else {
                val order = Order(orderId,name,totalOrderItem, totalOrderPrice)
                ref.child(orderId.toString()).setValue(order).addOnCompleteListener {
                    Toast.makeText(applicationContext, "Order saved successfully.", Toast.LENGTH_LONG).show()
                }
                startActivity(Intent(this,Payment::class.java))
            }
        }

}


