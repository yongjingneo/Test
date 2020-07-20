package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ReviewAdapter (val mCtx: Context, val layoutResId:Int, val reviewList: List<Review>)
    : ArrayAdapter<Review>(mCtx,layoutResId,reviewList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx);
        val view: View = layoutInflater.inflate(layoutResId,null)
        val textViewRating = view.findViewById<TextView>(R.id.textViewRating)

        val review = reviewList[position]
        textViewRating.text="Review id: " +review.id + "\nRating: " +review.rating +
                "\nReview: " +review.review


        return view
    }
}