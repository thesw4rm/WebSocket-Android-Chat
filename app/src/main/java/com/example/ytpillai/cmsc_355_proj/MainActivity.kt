package com.example.ytpillai.cmsc_355_proj

import android.content.Intent
import android.content.SharedPreferences
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*This activity is basically just a startup screen that goes to security lock.
        Go to ProgramActivity.kt for the applications actual content */

        val handler = Handler()
        handler.postDelayed({
            val preferences = getSharedPreferences("PREFS", 0)
            val password = preferences.getString("password", "0")
            if (password == "0") {
                val intent = Intent(applicationContext, CreatePasswordActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(applicationContext, InputPasswordActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)
    }
}
