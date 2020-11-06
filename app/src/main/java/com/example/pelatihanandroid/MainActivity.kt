package com.example.pelatihanandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getStartedButton.setOnClickListener {
            val email = emailAddressEditText.text.toString()
            val bla: String
            if(email.isEmpty()) {
                Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, NextActivity::class.java)
                intent.putExtra("SEND_EMAIL", email)
                startActivity(intent)
            }
        }
    }
}