package com.example.myapplication

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.admin.adminMainPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val actionbar = supportActionBar
        actionbar!!.title="Login"

        auth = FirebaseAuth.getInstance()

        txtReg.setOnClickListener{
            startActivity(Intent(this,Register::class.java))
            finish()
        }

        txtForgotPW.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")

            val view = layoutInflater.inflate(R.layout.dialog_forgot_password, null)
            val email = view.findViewById<EditText>(R.id.userEmail)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                forgotPassword(email)
            })
            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->  })
            builder.show()
        }

        btnLogin.setOnClickListener {
            if((txtLoginEmail.text.toString().trim() == "yj012999@gmail.com" && txtLoginPassword.text.toString().trim() ==
                        "abc123")|| (txtLoginEmail.text.toString().trim() == "ft2727@gmail.com"&& txtLoginPassword.text.toString().trim() ==
                    "abc123")){
                startActivity(Intent(this, adminMainPage::class.java))
            }else{
                doLogin(it)
            }
        }
    }

    private fun doLogin(view:View){
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

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }

    private fun forgotPassword(email:EditText){
        if(email.text.toString().isEmpty()){
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            return
        }

        auth.sendPasswordResetEmail(email.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Email sent.", Toast.LENGTH_SHORT).show()
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
