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

        txtLogin.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
            finish()
        }
    }

    private fun registerUser(){
        if(txtRegEmail.text.toString().isEmpty()){
            txtRegEmail.error = "Please enter email"
            txtRegEmail.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(txtRegEmail.text.toString()).matches()){
            txtRegEmail.error = "Please enter valid email"
            txtRegEmail.requestFocus()
            return
        }

        if(txtRegPassword.text.toString().isEmpty()){
            txtRegPassword.error = "Please enter password"
            txtRegPassword.requestFocus()
            return
        }

        if(txtRegPassword.text.toString().length<6){
            txtRegPassword.error = "Password must be at least 6 characters."
            txtRegPassword.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(txtRegEmail.text.toString(), txtRegPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this,Login::class.java))
                                finish()
                            }
                        }
                } else {
                    //txtRegEmail.error = "Email already registered."
                    Toast.makeText(applicationContext, "Email already registered.", Toast.LENGTH_SHORT).show()
                }
            }
    }

}
