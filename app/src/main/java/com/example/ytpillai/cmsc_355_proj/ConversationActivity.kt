package com.example.ytpillai.cmsc_355_proj

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.example.ytpillai.cmsc_355_proj.messaging.App
import com.example.ytpillai.cmsc_355_proj.messaging.Me
import com.example.ytpillai.cmsc_355_proj.messaging.Message
import com.example.ytpillai.cmsc_355_proj.messaging.MessageAdapter
import com.example.ytpillai.cmsc_355_proj.networking.MessageSocketClient
import com.example.ytpillai.cmsc_355_proj.services.MessageClientService
import com.example.ytpillai.cmsc_355_proj.services.MessageServerService
import com.tinder.scarlet.Scarlet
import kotlinx.android.synthetic.main.activity_conversation.*
import java.net.URI
import java.util.*
import okhttp3.OkHttpClient
import okhttp3.Request


private const val TAG = "ConversationActivity"

class ConversationActivity : AppCompatActivity() {


    private lateinit var adapter: MessageAdapter
    private lateinit var messageClientService: MessageClientService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)

        backArrowBtn.setOnClickListener {
            val intent = Intent(this, ContactsActivity::class.java)
            startActivity(intent)
            finish()
        }

        chatRecycler.layoutManager = LinearLayoutManager(this)
        adapter = MessageAdapter(this)
        chatRecycler.adapter = adapter

        val chatHeader: TextView = findViewById(R.id.nicknameHeader)
        chatHeader.text = App.nickname


        //TEMP
//        receiveMessage(message = "Testing...")


        sendBtn.setOnClickListener {
            if (chatbox.text.isNotEmpty()) {

                sendMessage()
                resetInput()

                //TEMP
//                receiveMessage(message = "Testing...")

            } else {
                Toast.makeText(this, "Please enter text to send", Toast.LENGTH_SHORT).show()
            }
        }

        messageClientService = MessageClientService()
        val messageClientServiceIntent = Intent(this, MessageClientService::class.java)
        messageClientServiceIntent.putExtra("destIP", "10.0.2.2")
        startService(messageClientServiceIntent)
        val destIP = "10.0.2.2"
        val uri = "ws://$destIP:8112/"
        //TODO: Destroy this somehow in onDestroy
        val request = Request.Builder().url(uri).build()
        val okHttpClient = OkHttpClient.Builder().build()

        val messageSocketClient = MessageSocketClient(URI(uri), applicationContext)
        val webSocket = okHttpClient.newWebSocket(request, messageSocketClient)

        val okHttpClientService = okHttpClient.dispatcher().executorService()


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

        //Log.e(TAG, message.toString())

        runOnUiThread {
            adapter.addMessage(message)
            chatRecycler.scrollToPosition(adapter.itemCount - 1)
        }
    }

    private fun receiveMessage(message: String) {
        val message = Message(
                App.ip,
                message,
                Calendar.getInstance().timeInMillis
        )

        runOnUiThread {
            adapter.addMessage(message)
            chatRecycler.scrollToPosition(adapter.itemCount - 1)
        }
    }

    private fun resetInput() {
        // Clean text box
        chatbox.text.clear()

        // Hide keyboard
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
