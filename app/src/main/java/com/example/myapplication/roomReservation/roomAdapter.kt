package com.example.myapplication.roomReservation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myapplication.R

class roomAdapter(val mCtx: Context, val layoutResId: Int, val roomList:List<roomReservation>):
    ArrayAdapter<roomReservation>(mCtx, layoutResId, roomList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater= LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)

        val txtRoomMail =view.findViewById<TextView>(R.id.txtRoomMail)
        val txtRoomDate =view.findViewById<TextView>(R.id.txtRoomDate)
        val txtRoomTime =view.findViewById<TextView>(R.id.txtRoomTime)
        val txtRoomName =view.findViewById<TextView>(R.id.txtRoomName)
        val txtRoomPhoneNo =view.findViewById<TextView>(R.id.txtRoomPhoneNo)

        val room = roomList[position]

        txtRoomName.text = room.nameR
        txtRoomPhoneNo.text = room.phoneNo
        txtRoomMail.text = room.mail
        txtRoomDate.text = room.date
        txtRoomTime.text = room.time

        return view
    }
}