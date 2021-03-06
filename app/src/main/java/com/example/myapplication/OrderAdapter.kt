package com.example.myapplication

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlin.math.roundToInt


class OrderAdapter (val mCtx: Context, val layoutResId:Int,val orderList: List<Order>)
    : ArrayAdapter<Order>(mCtx,layoutResId,orderList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx);
        val view:View = layoutInflater.inflate(layoutResId,null)
        val textViewName = view.findViewById<TextView>(R.id.textViewName)

        val order = orderList[position]
        textViewName.text="Customer email: "+ order.email+
                "\nOrdered Food: "+ order.orderedFood+ "\nOrder price in RM: "+ order.totalPrice +
                "\nPayment Amount in ₹: " + ((order.totalPrice.toDouble()/0.057).toInt())


        return view
    }
}