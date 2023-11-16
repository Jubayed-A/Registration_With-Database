package com.example.singupwithdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class singInActivity : AppCompatActivity() {

    private lateinit var databaseReference : DatabaseReference
    companion object{
        const val KEY1 = "com.example.singupwithdatabase.mail"
        const val KEY2 = "com.example.singupwithdatabase.name"
        const val KEY3 = "com.example.singupwithdatabase.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in)

        val signInButton = findViewById<Button>(R.id.btnSignIn)
        val userName = findViewById<TextInputEditText>(R.id.userNameEditText)
        val creatAccount = findViewById<TextView>(R.id.textNoAccount)

        signInButton.setOnClickListener {

            val uniqueId = userName.text.toString()
            if (uniqueId.isNotEmpty()){
                readData(uniqueId)
            } else{
                Toast.makeText(this, "Please enter user ID", Toast.LENGTH_SHORT).show()
            }

        }

        // go to signup page
        creatAccount.setOnClickListener {
            val intentSignUpPage = Intent(this, MainActivity::class.java)
            startActivity(intentSignUpPage)
        }

    }

    // new method for read from database
    private fun readData(uniqueId : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {
            //  check user exit or not
            if (it.exists()){
                // welcome user in your app, with intent and also pass
                val email = it.child("email").value
                val name = it.child("name").value
                val userId = it.child("uniqueId").value
                val newIntent = Intent(this, WelcomeActivity::class.java)
                newIntent.putExtra(KEY1, email.toString())
                newIntent.putExtra(KEY2, name.toString())
                newIntent.putExtra(KEY3, userId.toString())
                startActivity(newIntent)
            } else{
                Toast.makeText(this, "User does not exits, Please Sign Up first", Toast.LENGTH_SHORT).show()
            }
        } .addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}