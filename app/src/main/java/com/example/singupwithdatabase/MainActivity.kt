package com.example.singupwithdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signButton = findViewById<Button>(R.id.btnSingUp)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etUnique = findViewById<TextInputEditText>(R.id.etUniqueId)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val haveAccount = findViewById<TextView>(R.id.textAccount)

        // have account code here
        haveAccount.setOnClickListener {
            val intentSignIn = Intent(this, singInActivity::class.java)
            startActivity(intentSignIn)
        }

        // sign-button code here
        signButton.setOnClickListener {
            val name = etName.text.toString()
            val unique = etUnique.text.toString()
            val mail = etEmail.text.toString()
            val password = etPassword.text.toString()

            val user = User(name, unique, mail, password)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(unique).setValue(user).addOnSuccessListener {
                etName.text?.clear()
                etUnique.text?.clear()
                etEmail.text?.clear()
                etPassword.text?.clear()
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "User Registered Failed", Toast.LENGTH_SHORT).show()
            }

        }

    }
}