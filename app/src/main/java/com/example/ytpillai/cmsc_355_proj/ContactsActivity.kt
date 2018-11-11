package com.example.ytpillai.cmsc_355_proj

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.ytpillai.cmsc_355_proj.messaging.MessageAdapter
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.activity_conversation.*

class ContactsActivity : AppCompatActivity() {

    private lateinit var contactsAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        newConBtn.setOnClickListener{
            //Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ProgramActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        contactList.layoutManager = LinearLayoutManager(this)
        contactsAdapter = MessageAdapter(this)
        contactList.adapter = contactsAdapter
    }
}
