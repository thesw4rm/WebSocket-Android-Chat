package com.example.ytpillai.cmsc_355_proj

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_conversation.*

class ConversationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)

        backArrowBtn.setOnClickListener{
            val intent = Intent(this,Contacts::class.java)
            startActivity(intent)
        }
    }
}
