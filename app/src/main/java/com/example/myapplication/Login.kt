package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        txtReg.setOnClickListener{
            startActivity(Intent(this,Register::class.java))
            finish()
        }

        btnLogin.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin(){
        if(txtLoginEmail.text.toString().isEmpty()){
            txtLoginEmail.error = "Please enter email"
            txtLoginEmail.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(txtLoginEmail.text.toString()).matches()){
            txtLoginEmail.error = "Please enter valid email"
            txtLoginEmail.requestFocus()
            return
        }

        if(txtLoginPassword.text.toString().isEmpty()){
            txtLoginPassword.error = "Please enter password"
            txtLoginPassword.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(txtLoginEmail.text.toString(), txtLoginPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(baseContext, "Invalid email and/or password.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }

    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?){
        if(currentUser != null) {
            if(currentUser.isEmailVerified) {
                startActivity(Intent(this, MainPage::class.java))
                finish()
            }else{
                Toast.makeText(baseContext,"Plese verify your email address.",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
