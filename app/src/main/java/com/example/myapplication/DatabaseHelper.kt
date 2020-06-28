package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



val dbname = "ratingdb"
val col_rating="rating"
val col_review="review"



class DatabaseHelper(context: Context):SQLiteOpenHelper(context, dbname,null,1){
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table rating(id integer primary key autoincrement,"+
                col_rating + "varchar(5)"+
                col_review + "varchar(100)")
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val allData : Cursor
        get() {val db = this.writableDatabase
            return db.rawQuery("Select * from rating",null)
        }

    fun insertMealData(rating:String, review: String){
        val db: SQLiteDatabase = writableDatabase
        val values: ContentValues = ContentValues()
        values.put("rating",rating)
        values.put("review",review)

        db.insert("rating",null,values)
        db.close()
    }


    /*fun updateMealData(id:String, name: String, price: String, quantity: String):Boolean {
        val db: SQLiteDatabase = writableDatabase
        val values: ContentValues = ContentValues()
        values.put("name", name)
        values.put("price", price)*/


        /*val result =db.update("meal",values,"id = ?", arrayOf(id))

        return if(result>0) {
            true
        }else {
            false
        }
    }


    fun deleteMealData(id:String): Int?{
        val db: SQLiteDatabase = writableDatabase
        val i = db.delete("meal","id = ?", arrayOf(id))
        return i
    }

    fun mealExist(email: String, password: String): Boolean{
        val db = writableDatabase
        val query = "select * from meal where email='$email' and password='$password'"
        val cursor:Cursor = db.rawQuery(query,null)
        if(cursor.count<=0){
            cursor.close()
            return false
        }

        cursor.close()
        return true
    }*/

}