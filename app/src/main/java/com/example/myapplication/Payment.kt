package com.example.myapplication

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.gson.GsonBuilder
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import kotlinx.android.synthetic.main.activity_meal_ordering.*
import kotlinx.android.synthetic.main.activity_payment.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigDecimal
import java.time.temporal.TemporalAmount
import kotlin.math.roundToInt

class Payment : AppCompatActivity(), PaymentResultWithDataListener {

    lateinit var retrofit: Retrofit
    lateinit var retroInterface: RetrofitInterface

    lateinit var orderList: MutableList<Order>
    lateinit var ref: DatabaseReference
    lateinit var listViewData: ListView

    lateinit var auth: FirebaseAuth
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "com.example.myapplication"
    private val descrption = "Payment is done"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        auth = FirebaseAuth.getInstance()
        val email = auth.currentUser?.email

        Checkout.preload(applicationContext)
        val gson = GsonBuilder().setLenient()
        retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000")
            .addConverterFactory(GsonConverterFactory.create(gson.create()))
            .build()

        retroInterface = retrofit.create(RetrofitInterface::class.java)

        var intent = intent
        val price = intent.getStringExtra("Price")
        //val priceInr:String = String.format(price.toDouble()/0.057))
        val priceInr:String = String.format("%.2f", (price.toDouble()/0.057))


        val amountEdit: EditText = findViewById(R.id.amountEdit)
        amountEdit.setText(priceInr.toString().dropLast(3))




        findViewById<Button>(R.id.btnRazar).setOnClickListener{

            val amountEdit: EditText = findViewById(R.id.amountEdit)
            val amount = amountEdit.text.toString()


            if(amount.isEmpty()){
                amountEdit.error = "Please enter an amount"
                return@setOnClickListener

            }

            getOrderId(amount)
        }


        orderList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("orders")



        listViewData = findViewById(R.id.listViewData)

        val actionbar = supportActionBar
        actionbar!!.title="Payment"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                if(p0!!.exists()){
                    for (h in p0.children){
                        if(h.getValue(Order::class.java)?.email == email)
                        {val order = h.getValue(Order::class.java)
                            orderList.add(order!!)}

                    }

                    val adapter = OrderAdapter(applicationContext,R.layout.orders,orderList)
                    listViewData.adapter = adapter
                }
            }

        })

    }


    private fun getOrderId(amount: String){
        val map = HashMap<String, String>()
        map["amount"]=amount

        retroInterface
            .getOrderId(map).enqueue(object: Callback<Orders>{
                override fun onFailure(call: Call<Orders>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<Orders>, response: Response<Orders>) {
                    if(response.body() != null)
                        initiatePayment(amount, response.body()!!)
                }

            })
    }

    private fun initiatePayment(amount: String, order: Orders){
        val checkout = Checkout()
        checkout.setKeyID(order.getKeyId())

        val paymentOptions = JSONObject()
        paymentOptions.put("name", "Long Fatt Restaurant")
        paymentOptions.put("amount", amount)
        paymentOptions.put("order_id", order.getOrderId())
        paymentOptions.put("currency", "INR")
        paymentOptions.put("description", "Reference no: #1234")

        checkout.open(this, paymentOptions)
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(this,"Payment failed", Toast.LENGTH_LONG).show()
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        val map = HashMap<String,String>()
        map["order_id"]=p1!!.orderId
        map["pay_id"]=p1.paymentId
        map["signature"]=p1.signature

        retroInterface.updateTransaction(map)
            .enqueue(object :Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(this@Payment,t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.body().equals("success")) {
                        Toast.makeText(this@Payment, "Payment success", Toast.LENGTH_LONG).show()
                    }

                }

            })

    }
}