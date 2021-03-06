package com.example.myapplication.tableReservation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myapplication.R

class tableAdapter(val mCtx: Context, val layoutResId: Int, val tableList:List<tableReservation>):
    ArrayAdapter<tableReservation>(mCtx, layoutResId, tableList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater= LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)

        val txtTableName =view.findViewById<TextView>(R.id.txtTableName)
        val txtTablePhoneNo =view.findViewById<TextView>(R.id.txtTablePhoneNo)
        val txtTableMail =view.findViewById<TextView>(R.id.txtTableMail)
        val txtTableDate =view.findViewById<TextView>(R.id.txtTableDate)
        val txtTableTime =view.findViewById<TextView>(R.id.txtTableTime)
        val txtTableSize =view.findViewById<TextView>(R.id.txtTableSize)
        val txtTableNo =view.findViewById<TextView>(R.id.txtTableNo)

        val table = tableList[position]

        txtTableName.text = table.nameT
        txtTablePhoneNo.text = table.phoneNo
        txtTableMail.text = table.mail
        txtTableDate.text = table.date
        txtTableTime.text = table.time
        txtTableSize.text = table.bigTable.toString()
        txtTableNo.text = table.smallTable.toString()

        return view
    }
}