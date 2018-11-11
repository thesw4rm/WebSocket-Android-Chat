package com.example.ytpillai.cmsc_355_proj

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.ytpillai.cmsc_355_proj.messaging.*
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.activity_conversation.*
import java.util.*

private const val TAG = "ContactsActivity"

class ContactsActivity : AppCompatActivity() {

    private lateinit var contactsAdapter: ContactsAdapter

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
        contactsAdapter = ContactsAdapter(this)
        contactList.adapter = contactsAdapter

        newContact()



    }

    private fun newContact(){

//        val getNickname = findViewById<TextView>(R.id.nicknameContactList)
//        val contactNickname1 = getNickname.text.toString()
        val contactNickname1 = App.nickname
        val contactIP1 = App.ip

//        val getIP = findViewById<TextView>(R.id.ipAddressContactList)

        val contact = Contact(
                contactNickname1,
                contactIP1,
                Calendar.getInstance().timeInMillis

        )

        Log.e(TAG, contact.toString())

        //Log.e(TAG, message.toString())

        runOnUiThread {
            contactsAdapter.addContact(contact)
//            chatRecycler.scrollToPosition(contactsAdapter.itemCount - 1)
        }

    }




}
