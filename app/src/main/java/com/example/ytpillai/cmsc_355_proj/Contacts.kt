package com.example.ytpillai.cmsc_355_proj

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_contacts.*

class Contacts : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        newConBtn.setOnClickListener{
            val intent = Intent(this,ProgramActivity::class.java)
            startActivity(intent)
        }
    }
}
