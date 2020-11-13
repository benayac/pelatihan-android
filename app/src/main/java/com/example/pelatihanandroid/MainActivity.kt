package com.example.pelatihanandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        facebookButton.setOnClickListener {
            Toast.makeText(applicationContext, "Facebook", Toast.LENGTH_SHORT).show()
        }
        appleButton.setOnClickListener {
            Toast.makeText(applicationContext, "Apple", Toast.LENGTH_SHORT).show()

        }

        googleButton.setOnClickListener {
            Toast.makeText(applicationContext, "Google", Toast.LENGTH_SHORT).show()
        }

        getStartedButton.setOnClickListener {
            val intent = Intent(this, ShowDataActivity::class.java)
            val data = getEditText()
            intent.putExtra("EXTRA_DATA", data)
            startActivity(intent)
        }
    }

    fun getEditText(): String {
        return emailAddressEditText.text.toString()
    }
}