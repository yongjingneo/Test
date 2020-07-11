package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
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
            var selected:String = ""

            if(checkBox.isChecked  && q1.text.isNotEmpty()){
                total = total + q1.text.toString().toInt()
                price = price + rate1.text.toString().toDouble()*q1.text.toString().toDouble()
                selected = selected + checkBox.text.toString()
            }

            if(checkBox2.isChecked && q2.text.isNotEmpty()){
                total = total + q2.text.toString().toInt()
                price = price + rate2.text.toString().toDouble()*q2.text.toString().toDouble()
                selected = selected + ","+ checkBox2.text.toString()
            }

            if(checkBox3.isChecked && q3.text.isNotEmpty()){
                total = total + q3.text.toString().toInt()
                price = price + rate3.text.toString().toDouble()*q3.text.toString().toDouble()
                selected = selected + "," + checkBox3.text.toString()
            }

            if(checkBox4.isChecked && q4.text.isNotEmpty()){
                total = total + q4.text.toString().toInt()
                price = price + rate4.text.toString().toDouble()*q4.text.toString().toDouble()
                selected = selected + "," + checkBox4.text.toString()
            }

            if(checkBox5.isChecked && q5.text.isNotEmpty()){
                total = total + q5.text.toString().toInt()
                price = price + rate5.text.toString().toDouble()*q5.text.toString().toDouble()
                selected = selected + "," + checkBox5.text.toString()
            }

            if(checkBox6.isChecked && q6.text.isNotEmpty()){
                total = total + q6.text.toString().toInt()
                price = price + rate6.text.toString().toDouble()*q6.text.toString().toDouble()
                selected = selected + "," + checkBox6.text.toString()
            }

            if(checkBox7.isChecked && q7.text.isNotEmpty()){
                total = total + q7.text.toString().toInt()
                price = price + rate7.text.toString().toDouble()*q7.text.toString().toDouble()
                selected = selected + "," + checkBox7.text.toString()
            }

            if(checkBox8.isChecked && q8.text.isNotEmpty()){
                total = total + q8.text.toString().toInt()
                price = price + rate8.text.toString().toDouble()*q8.text.toString().toDouble()
                selected = selected + "," + checkBox8.text.toString()
            }

            if(checkBox9.isChecked && q9.text.isNotEmpty()){
                total = total + q9.text.toString().toInt()
                price = price + rate9.text.toString().toDouble()*q9.text.toString().toDouble()
                selected = selected + "," + checkBox9.text.toString()
            }

            totalItem.setText("Total item ordered\t\t\t" + total.toString())
            totalPrice.setText("Total Price\t\t\t" + String.format("%.2f", price).toDouble())
            selectedFood.setText("Food ordered:\t\t\t" + selected.toString())


            val totalOrderItem =total.toString()
            //val totalOrderPrice =price.toString()
            val totalOrderPrice =String.format("%.2f", price)
            val name = custName.text.toString().trim()
            val selectfood = selected.toString().trim()


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
                val order = Order(orderId,name,totalOrderItem, totalOrderPrice,selectfood)
                ref.child(orderId.toString()).setValue(order).addOnCompleteListener {
                    Toast.makeText(applicationContext, "Order saved successfully.", Toast.LENGTH_LONG).show()
                }
                startActivity(Intent(this, Payment::class.java))
            }
        }

}


