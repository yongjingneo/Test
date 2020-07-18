package com.example.myapplication

class Review(val id: String?, val review:String, val rating: Int){
    constructor(): this("","",12345)
}
