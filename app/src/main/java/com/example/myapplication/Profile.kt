package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_register.*

class Profile : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()
        displayProfile()

        btnChgPassword.setOnClickListener {
            startActivity(Intent(this, ChangePassword::class.java))
        }
    }

    private fun displayProfile(){
        val user = auth.currentUser
        if(user != null){
            user?.let {
                val name = user.displayName
                val email = user.email

                txtName.setText(name)
                txtEmail.setText(email)
            }
        }

    }
}
