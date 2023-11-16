package com.example.singupwithdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val name = intent.getStringExtra(singInActivity.KEY2)
        val mail = intent.getStringExtra(singInActivity.KEY1)
        val userId = intent.getStringExtra(singInActivity.KEY3)


        val textMail = findViewById<TextView>(R.id.txtMail)
        val id = findViewById<TextView>(R.id.txtUniqueID)
        val welcomeName = findViewById<TextView>(R.id.txtDisplay)

        welcomeName.text = "Welcome $name"
        textMail.text = "Mail : $mail"
        id.text = "UserID : $userId"


    }
}