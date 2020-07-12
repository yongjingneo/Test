package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_profile.*

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

        btnCheck.setOnClickListener {
            startActivity(Intent(this, Reservation::class.java))
        }
    }

    private fun displayProfile(){
        val user = auth.currentUser
        if(user != null){

//            if(user.displayName == null){
//                val builder = AlertDialog.Builder(this)
//                builder.setTitle("Set Username")
//
//                val view = layoutInflater.inflate(R.layout.dialog_set_name, null)
//                val name = view.findViewById<EditText>(R.id.txtUsername)
//                builder.setView(view)
//                builder.setPositiveButton("Set", DialogInterface.OnClickListener { _, _ ->
//                    setUsername(name)
//                })
//                builder.show()
//            }

            txtEmail.setText(user.email)
            //updateProfile()

        }
    }

//    private fun updateProfile(){
//        val user = auth.currentUser
//        if(user != null){
//            user?.let {
//                val name = user.displayName
//                val email = user.email
//
//                txtName.setText(name.toString())
//                txtEmail.setText(email.toString())
//            }
//        }
//    }

//    private fun setUsername(name: EditText){
//        val user = auth.currentUser
//
//        val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(name.toString()).build()
//
//        user!!.updateProfile(profileUpdates).addOnCompleteListener { task ->
//            if(task.isSuccessful){
//                Toast.makeText(this, "Username Updated.", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
}
