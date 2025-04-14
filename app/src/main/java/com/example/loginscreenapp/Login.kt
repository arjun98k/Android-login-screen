package com.example.loginscreenapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {


    public override fun onStart() {
        super.onStart()
        val auth = FirebaseAuth.getInstance()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.secondacitivty)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.secee)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val btn = findViewById<MaterialButton>(R.id.btn)
        val  edtemailtxt = findViewById<EditText>(R.id.edt2)
        val passwordtxt = findViewById<EditText>(R.id.edt3)
        val textview = findViewById<TextView>(R.id.login)
        val auth = FirebaseAuth.getInstance()


        textview.setOnClickListener {


            intent = Intent(this, Register::class.java)
            startActivity(intent)

        }

        btn.setOnClickListener {
            val email = edtemailtxt.text.toString()
            val password = passwordtxt.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(applicationContext, "enter email and password", Toast.LENGTH_SHORT).show()
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }
        }



    }
}