package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePassword : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val actionbar = supportActionBar
        actionbar!!.title="Change Password"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()

        btnConfirm.setOnClickListener {
            changePassword(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun changePassword(view: View){
        if(txtCurrentPW.text.isNotEmpty() && txtNewPW.text.isNotEmpty() && txtConfirmPW.text.isNotEmpty()){
            if(txtNewPW.text.toString().equals(txtConfirmPW.text.toString())){

                val user = auth.currentUser
                if(user != null && user.email != null){
                    val credential = EmailAuthProvider
                        .getCredential(user.email!!, txtCurrentPW.text.toString())

                    user.reauthenticate(credential)
                        .addOnCompleteListener {
                            if(it.isSuccessful){
                                //Toast.makeText(this, "Authentication successful.", Toast.LENGTH_SHORT).show()
                                user.updatePassword(txtNewPW.text.toString())
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(this, "Password changed successfully.", Toast.LENGTH_SHORT).show()
                                            txtCurrentPW.setText("")
                                            txtNewPW.setText("")
                                            txtConfirmPW.setText("")
                                        }
                                    }
                            }else{
                                Toast.makeText(this, "Invalid current password", Toast.LENGTH_SHORT).show()
                            }
                        }

                }else{
                    startActivity(Intent(this, Login::class.java))
                    finish()
                }

            }else{
                Toast.makeText(this, "New password and confirm password are not matched.", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Please fill all the fields.", Toast.LENGTH_SHORT).show()
        }

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }
}
