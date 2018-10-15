package com.example.ytpillai.cmsc_355_proj

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View


class ProgramActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program)
        
    }

    /*This is the activity that is loaded after user creates password for the first time or enters correct password*/

    fun ChangeActivity(view: View) {
        val intent = Intent(this, OtherActivity::class.java)
        startActivity(intent)
    }

}