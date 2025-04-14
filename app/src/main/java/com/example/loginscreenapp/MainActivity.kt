 package com.example.loginscreenapp

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth

 class MainActivity : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         enableEdgeToEdge()
         setContentView(R.layout.activity_main)
         ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
             val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
             v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
             insets
         }
         val btn = findViewById<Button>(R.id.logout)
         val txt = findViewById<TextView>(R.id.user_detail)

         val auth = FirebaseAuth.getInstance()
         var user = auth.currentUser

         user = auth.currentUser

         if (user != null) {
             val intent = Intent(applicationContext, Login::class.java)
             startActivity(intent)
         } else {
             txt.text = user?.email ?: "No user"
         }
         btn.setOnClickListener {
             auth.signOut()
             val intent = Intent(applicationContext, Login::class.java)
             startActivity(intent)
             finish()

         }
     }
 }