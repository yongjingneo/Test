package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val actionbar = supportActionBar
        actionbar!!.title="Register"

        auth = FirebaseAuth.getInstance()

        btnReg.setOnClickListener {
            registerUser(it)
        }

        txtLogin.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
            finish()
        }
    }

    private fun registerUser(view:View){
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
                        .addOnCompleteListener { _ ->
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

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
