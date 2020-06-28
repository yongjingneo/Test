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
        if(txtEmail.text.toString().isEmpty()){
            txtEmail.error = "Please enter email"
            txtEmail.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(txtEmail.text.toString()).matches()){
            txtEmail.error = "Please enter valid email"
            txtEmail.requestFocus()
            return
        }

        if(txtRegPassword.text.toString().isEmpty()){
            txtRegPassword.error = "Please enter password"
            txtRegPassword.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(txtEmail.text.toString(), txtRegPassword.text.toString())
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
                    Toast.makeText(baseContext, "Register failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}
