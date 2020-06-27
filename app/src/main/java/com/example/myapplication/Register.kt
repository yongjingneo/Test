package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        btnReg.setOnClickListener {
            registerUser()
        }
    }
    fun registerUser(){
        if(txtEmailReg.text.toString().isEmpty()){
            txtEmailReg.error = "Please enter email"
            txtEmailReg.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(txtEmailReg.text.toString()).matches()){
            txtEmailReg.error = "Please enter valid email"
            txtEmailReg.requestFocus()
            return
        }

        if(txtPassword.text.toString().isEmpty()){
            txtPassword.error = "Please enter password"
            txtPassword.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(txtEmailReg.text.toString(), txtPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this,Login::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Register failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}
