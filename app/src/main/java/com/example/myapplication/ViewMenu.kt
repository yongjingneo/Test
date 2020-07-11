package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import com.example.myapplication.com.example.myapplication.adapters.FoodAdapters
import com.example.myapplication.com.example.myapplication.model.Food
import kotlinx.android.synthetic.main.activity_view_menu.*

class ViewMenu : AppCompatActivity() {

    private var arrayList:ArrayList<Food> ? = null
    private var gridView:GridView ? = null
    private var foodAdapters: FoodAdapters ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_menu)

        val actionbar = supportActionBar
        actionbar!!.title="View Menu"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        gridView = findViewById(R.id.my_grid_view_list)
        arrayList = ArrayList()
        arrayList = setDataList()
        foodAdapters = FoodAdapters(applicationContext, arrayList!!)
        gridView?.adapter = foodAdapters

        btnNextPage.setOnClickListener{
            startActivity(Intent(this,MealOrdering::class.java))
        }
    }

    private fun setDataList(): ArrayList<Food> {

        var arrayList: ArrayList<Food> = ArrayList()

        arrayList.add(Food(R.drawable.chick1,"Sweet & Sour Chicken RM29.00"))
        arrayList.add(Food(R.drawable.chick2,"Kam Heong Chicken  RM25.00"))
        arrayList.add(Food(R.drawable.chick3,"Curry Chicken RM39.00"))
        arrayList.add(Food(R.drawable.fish1,"Curry Fish   RM70.00"))
        arrayList.add(Food(R.drawable.fish2,"Sambal Fish   RM45.00"))
        arrayList.add(Food(R.drawable.fish3,"Steam Fish   RM59.00"))
        arrayList.add(Food(R.drawable.prawn1,"Onion Prawn   RM55.00"))
        arrayList.add(Food(R.drawable.prawn2,"Salted Egg Prawn   RM62.00"))
        arrayList.add(Food(R.drawable.prawn3,"Chilli Prawn   RM60.00"))

        return arrayList
    }
}
