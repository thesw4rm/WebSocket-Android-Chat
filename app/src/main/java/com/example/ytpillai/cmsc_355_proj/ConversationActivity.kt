package com.example.ytpillai.cmsc_355_proj

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.ytpillai.cmsc_355_proj.messaging.App
import com.example.ytpillai.cmsc_355_proj.messaging.Me
import com.example.ytpillai.cmsc_355_proj.messaging.Message
import com.example.ytpillai.cmsc_355_proj.messaging.MessageAdapter
import com.example.ytpillai.cmsc_355_proj.services.MessageClientService
import kotlinx.android.synthetic.main.activity_conversation.*
import java.util.*


class ConversationActivity : AppCompatActivity() {


    private lateinit var adapter: MessageAdapter
    private lateinit var messageClientService: MessageClientService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)

        backArrowBtn.setOnClickListener {
            val intent = Intent( this, ContactsActivity::class.java)
            startActivity(intent)
            finish()
        }

        chatRecycler.layoutManager = LinearLayoutManager(this)
        adapter = MessageAdapter(this)
        chatRecycler.adapter = adapter


        //Display chat messages bottom top
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        chatRecycler.layoutManager = layoutManager


        val chatHeader: TextView = findViewById(R.id.nicknameHeader)
        chatHeader.text = App.nickname


        sendBtn.setOnClickListener {
            if (chatbox.text.isNotEmpty()) {

                sendMessage()
                resetInput()

            } else {
                Toast.makeText(this, "Please enter text to send", Toast.LENGTH_SHORT).show()
            }
        }

        val messageClientServiceIntent = Intent(this, MessageClientService::class.java)
        messageClientServiceIntent.putExtra("destIP", App.ip)
        startService(messageClientServiceIntent)

        val messageBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent!!.action!!.equals(resources.getString(R.string.ACTION_RECEIVED_MESSAGE))) {
                    StringBuilder().apply {
                        append("Message received: ${intent.extras!!["message"]}")
                        toString().also { log ->
                            Log.d("PROGRAM_ACTIVITY", "Message received : ${intent.extras!!["message"]}")

                        }
                    }
                    receiveMessage("${intent.extras!!["message"]}")
                } else {
                    Log.d("PROGRAM_ACTIVITY", "Something received : ${intent.extras!!["message"]}")
                }
            }

        }
        registerReceiver(messageBroadcastReceiver, IntentFilter(resources.getString(R.string.ACTION_RECEIVED_MESSAGE)))
    }


    private fun sendMessage() {

        Me.ip = "127.0.0.1"
        val message = Message(
//                App.nickname,
                Me.ip,
                chatbox.text.toString(),
                Calendar.getInstance().timeInMillis
        )

        runOnUiThread {
            adapter.addMessage(message)
            chatRecycler.scrollToPosition(adapter.itemCount - 1)
        }
        Intent().also { intent ->
            intent.action = applicationContext.resources.getString(R.string.ACTION_SENT_MESSAGE)
            intent.putExtra("message", chatbox.text.toString())
            applicationContext.sendBroadcast(intent)
        }
    }

    private fun receiveMessage(message: String) {
        val messageObject = Message(
                App.ip,
                message,
                Calendar.getInstance().timeInMillis
        )

        runOnUiThread {
            adapter.addMessage(messageObject)
            chatRecycler.scrollToPosition(adapter.itemCount - 1)
        }
    }

    private fun resetInput() {
        // Clean text box
        chatbox.text.clear()

        // Hide keyboard
        /*val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)*/
    }
}
